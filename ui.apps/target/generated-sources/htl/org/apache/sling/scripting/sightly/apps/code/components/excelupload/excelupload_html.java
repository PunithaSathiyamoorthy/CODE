/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.code.components.excelupload;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class excelupload_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

out.write("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\"/>\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n<title>Upload Excel File</title>\n<style>\n        body {\n            font-family: Arial, sans-serif;\n            margin: 0;\n            padding: 20px;\n        }\n        #uploadForm {\n            max-width: 500px;\n            margin: auto;\n            background-color: #f9f9f9;\n            padding: 20px;\n            border-radius: 5px;\n        }\n        #uploadForm label {\n            display: block;\n            margin-bottom: 10px;\n        }\n        #uploadForm input[type=\"text\"],\n        #uploadForm input[type=\"file\"] {\n            width: calc(100% - 22px); /* Adjusted width to accommodate the checkbox */\n            padding: 10px;\n            margin-bottom: 15px;\n            border: 1px solid #ccc;\n            border-radius: 4px;\n            box-sizing: border-box;\n            display: inline-block; /* Aligns input elements horizontally */\n        }\n        #uploadForm .checkbox-container {\n            text-align: center; /* Center aligns the checkbox container */\n        }\n        #uploadForm input[type=\"checkbox\"] {\n            width: 20px; /* Adjusted width for the checkbox */\n            margin-top: 5px; /* Aligns the checkbox vertically */\n        }\n        #uploadButton {\n            display: block;\n            margin: 0 auto; /* Center aligns the button */\n            background-color: #4CAF50;\n            color: white;\n            padding: 10px 20px;\n            border: none;\n            border-radius: 4px;\n            cursor: pointer;\n            font-size: 16px;\n        }\n        #uploadButton:hover {\n            background-color: #45a049;\n        }\n    </style>\n</head>\n<body>\n<form id=\"uploadForm\" enctype=\"multipart/form-data\">\n        <div>\n            <label for=\"expFragment\">Excel Upload Component:</label>\n            <input type=\"text\" id=\"expFragment\" name=\"expFragment\"/>\n        </div>\n\t\t<input type=\"file\" name=\"excelFile\" id=\"excelFile\" accept=\".xlsx, .xls\"/>\n        <div>\n            <input type=\"checkbox\" id=\"activate\" name=\"activate\"/>\n            <label for=\"activate\">Activate</label>\n        </div>\n    <button type=\"submit\" id=\"uploadButton\">Upload</button>\n</form>\n\n<script>\n    $('#uploadForm').on('submit', function (e) {\n            e.preventDefault();\n\n           var fileInput = $('#excelFile')[0].files[0];\n           var customExp = $('#expFragment').val();\n           var activate = $(\"#activate\").is(\":checked\");\n           var formData = new FormData();\n           formData.append('file', fileInput);\n           formData.append('xfPath',customExp);\n           formData.append('activate',activate);\n            \n            $.ajax({\n                url: '/bin/upload/excel',\n                type: 'POST',\n                data: formData,\n                processData: false,\n                contentType: false,\n                success: function (response) {\n                    console.log('Upload successful:', response);\n                    console.log(formData);\n                    alert(response);\n                },\n                error: function (xhr, status, error) {\n                    console.error('Upload failed:', error);\n                    alert('Error occurred while uploading file.');\n                }\n            });\n        });\n</script>\n</body>\n</html>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

