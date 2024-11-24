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
package org.apache.sling.scripting.sightly.apps.code.components.hello;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class hello_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

out.write("<!DOCTYPE html>\n<html>\n<head>\n    <title>Form with reCAPTCHA</title>\n    <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n    <script src=\"https://www.google.com/recaptcha/enterprise.js?render=6Ld1zlEqAAAAABznLAY22Rr6K4WDNRzBgZgJSbLd\"></script>\n</head>\n<body>\n<form id=\"demo-form\" action=\"/bin/validateCaptcha\" method=\"POST\">\n    <input type=\"text\" name=\"name\" placeholder=\"Your Name\" required/>\n    <input type=\"email\" name=\"email\" placeholder=\"Your Email\" required/>\n    <input type=\"hidden\" id=\"g-recaptcha-response\" name=\"g-recaptcha-response\"/>\n\n    <button type=\"submit\" class=\"g-recaptcha\" data-sitekey=\"6Ld1zlEqAAAAABznLAY22Rr6K4WDNRzBgZgJSbLd\" data-callback='onSubmit' data-action='submit'>\n        Submit\n    </button>\n</form>\n<div id=\"responseMessage\"></div>\n\n<script>\n    $(document).ready(function() {\n        // Initialize reCAPTCHA\n        grecaptcha.ready(function() {\n            console.log(grecaptcha); // Check if grecaptcha is defined\n            updateCaptcha();\n            setInterval(updateCaptcha, 90000);\n        });\n\n        function updateCaptcha() {\n            grecaptcha.execute('6Ld1zlEqAAAAABznLAY22Rr6K4WDNRzBgZgJSbLd', {action: 'submit'}).then(function(token) {\n                $(\"#g-recaptcha-response\").val(token);\n            });\n        }\n\n        $(\"#demo-form\").on('submit', function(e) {\n            e.preventDefault();\n            if ($(\"#g-recaptcha-response\").val()) {\n                $.ajax({\n                    type: 'POST',\n                    url: '/bin/validateCaptcha?token=' + $(\"#g-recaptcha-response\").val(),\n                    dataType: 'text',\n                    success: function(response) {\n                        console.log('Response:', response);\n                    },\n                    error: function(xhr, status, error) {\n                        console.error('AJAX error:', error);\n                    }\n                });\n            }\n        });\n    });\n</script>\n</body>\n</html>\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

