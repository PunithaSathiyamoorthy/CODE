package com.adobe.aem.code.core.servlets;


import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = Servlet.class, property = {
        ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/upload/excel",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_POST })
public class ExcelUploadServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(ExcelUploadServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        log.info("Servlet has started...");
        ResourceResolver resourceResolver = request.getResourceResolver();
        if (!ServletFileUpload.isMultipartContent(request)) {
            log.info("IF condition for servletfile upload");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: No file uploaded");
            return;
        }
        try {
            BeanForExcel bean = new BeanForExcel();
            List<BeanForExcel> beanList = new ArrayList<>();
            log.info("inside try condition");
            InputStream inputStream = request.getPart("file").getInputStream();

            String xfPath1 = request.getParameter("xfPath");

            log.info("XF Path:{} ", xfPath1);
            boolean activate = Boolean.parseBoolean(request.getParameter("activate"));
            log.info("Activate Button {}", activate);
            Workbook workbook = WorkbookFactory.create(inputStream);
            for (Sheet sheet : workbook) {
                for (Row row : sheet) {

                    DataFormatter dataFormatter = new DataFormatter();
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        String cellValue = dataFormatter.formatCellValue(cell);

                        switch (i) {
                            case 0:
                                bean.setDam(cellValue);
                                break;
                            case 1:
                                bean.setDamAttribute(cellValue);
                                break;
                            case 2:
                                bean.setRegion(cellValue);
                                break;
                            default:
                                break;
                        }
                        beanList.add(bean);
                    }

                    String xfPath = "/content/experience-fragments/code/us/en/site/header";

                    if (checkXfPathExists(xfPath, resourceResolver)) {
                        log.info("checking x path present or not");
                        log.info("XF Path:{} ", xfPath1);
                        log.info("Activate Button {}", activate);
                        // storeAttributeInXfNode(dmaAttribute, dma, region, xfPath, resourceResolver);
                        storeAttributeInXfNode(beanList, xfPath, resourceResolver);
                    }
                }
            }

            response.getWriter().write("Excel file uploaded and processed successfully");
            log.info("response {}", response);
        } catch (Exception e) {
            log.error("Error processing Excel file", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error processing Excel file");
        } finally {
            if (resourceResolver != null) {
                resourceResolver.close();
            }
        }
    }

    private boolean checkXfPathExists(String xfPath, ResourceResolver resourceResolver) {
        Resource xfResource = resourceResolver.getResource(xfPath);
        return xfResource != null;
    }

    private void storeAttributeInXfNode(List<BeanForExcel> bean, String xfPath,
                                        ResourceResolver resourceResolver) throws PersistenceException {
        TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
        Tag tag = tagManager.resolve("/content/cq:tags/maintag");
        Iterator<Tag> listChildren = tag.listChildren();
        log.info("Tag{}, ListChidren{}", tag, listChildren);
        Resource xfResource = resourceResolver.getResource(xfPath);
        if (xfResource != null) {
            ModifiableValueMap properties = xfResource.adaptTo(ModifiableValueMap.class);
            if (properties != null) {

                for (BeanForExcel obj1 : bean) {
                    while (listChildren.hasNext()) {
                        Tag listChild = listChildren.next();
                        String tagTitle = listChild.getTitle().toString();
                        log.info("list Child {},;listChild {}", tagTitle, listChild);
                        String trimTagTitle = tagTitle.trim();
                        String replace = trimTagTitle.replaceAll("[./]", "");

                        if (replace.equals(obj1.getRegion().trim())) {
                            log.info("tagTitle{},obj.getRegion{}", tagTitle, obj1.getRegion());
                            log.info("Comparing values {}", replace.equals(obj1.getRegion().trim()));
                        }
                    }
                    properties.put("damAttribute", obj1.getDamAttribute());
                    properties.put("dam", obj1.getDam());
                    properties.put("region", obj1.getRegion());
                    // log.info("dam {},damAttribute{},region {}", obj1.getDam(),
                    // obj1.getDamAttribute(),
                    // obj1.getRegion());
                }
                log.info(xfPath);

                resourceResolver.commit();
                // log.info("Attribute '{}' {} ,'{} stored in experience fragment node '{}' '",
                // bean.getDamAttribute(),
                // bean.getDam(), bean.getRegion(), xfPath);
            } else {
                log.error("Unable to obtain modifiable properties for experience fragment node '{}'", xfPath);
            }
        } else {
            log.error("Experience fragment node '{}' not found", xfPath);
        }
    }

}