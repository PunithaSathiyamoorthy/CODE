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
package org.apache.sling.scripting.sightly.apps.code.components.helloworld;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class helloworld_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

out.write("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\"/>\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n<title>Dynamic State Field</title>\n</head>\n<body>\n\n<select id=\"country\" onchange=\"changeState()\">\n  <option value=\"\">Select Country</option>\n  <option value=\"USA\">USA</option>\n  <option value=\"UK\">UK</option>\n  <!-- Add more options as needed -->\n</select>\n\n<select id=\"state\" disabled>\n  <option value=\"\">Select State</option>\n  <!-- States for USA -->\n  <optgroup label=\"USA\">\n    <option value=\"California\">California</option>\n    <option value=\"New York\">New York</option>\n    <!-- Add more states as needed -->\n  </optgroup>\n  <!-- States for UK -->\n  <optgroup label=\"UK\">\n    <option value=\"London\">London</option>\n    <option value=\"Manchester\">Manchester</option>\n    <!-- Add more states as needed -->\n  </optgroup>\n  <!-- Add more optgroups for other countries as needed -->\n</select>\n\n<script>\nfunction changeState() {\n  var country = document.getElementById(\"country\").value;\n  var stateSelect = document.getElementById(\"state\");\n  \n  // Enable state dropdown\n  stateSelect.disabled = false;\n  \n  // Clear previous options\n  stateSelect.innerHTML = \"<option value=''>Select State</option>\";\n  \n  // Populate states based on selected country\n  switch(country) {\n    case \"USA\":\n      stateSelect.innerHTML += `\n        <optgroup label=\"USA\">\n          <option value=\"California\">California</option>\n          <option value=\"New York\">New York</option>\n          <!-- Add more states as needed -->\n        </optgroup>`;\n      break;\n    case \"UK\":\n      stateSelect.innerHTML += `\n        <optgroup label=\"UK\">\n          <option value=\"London\">London</option>\n          <option value=\"Manchester\">Manchester</option>\n          <!-- Add more states as needed -->\n        </optgroup>`;\n      break;\n    // Add cases for other countries as needed\n    default:\n      stateSelect.disabled = true;\n  }\n}\n</script>\n\n</body>\n</html>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

