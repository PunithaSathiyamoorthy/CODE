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
package org.apache.sling.scripting.sightly.apps.code.components.homeloanemicalc;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class homeloanemicalc_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_model = null;
_global_model = renderContext.call("use", com.adobe.aem.code.core.models.HomeLoanEmiCalcModel.class.getName(), obj());
out.write("<div></div>\n<p>");
{
    String var_0 = (" loanamount: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_model, "loan_amount"), "text")));
    out.write(renderContext.getObjectModel().toString(var_0));
}
out.write("</p>\n<p>");
{
    String var_1 = (" Interest: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_model, "interest"), "text")));
    out.write(renderContext.getObjectModel().toString(var_1));
}
out.write("</p>\n<p>");
{
    String var_2 = (" Tenure: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_model, "tenure"), "text")));
    out.write(renderContext.getObjectModel().toString(var_2));
}
out.write("</p>\n<p>");
{
    String var_3 = (" FinalResult: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_model, "finalresults"), "text")));
    out.write(renderContext.getObjectModel().toString(var_3));
}
out.write("</p>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

