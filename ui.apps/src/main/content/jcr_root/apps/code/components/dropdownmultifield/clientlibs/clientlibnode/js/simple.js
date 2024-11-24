(function (document, $) {
    "use strict";

    $(document).on("foundation-contentloaded", function (e) {
        Coral.commons.ready(function () {
            showHideHandler($(".cq-dialog-dropdown-showhide", e.target));
        });
    });

    $(document).on("change", ".cq-dialog-dropdown-showhide", function () {
        if ($(this).closest('.coral3-Multifield').length > 0) {
            showHideHandler($(this));
        }
    });

    function showHideHandler(el) {
        el.each(function (i, element) {
            if ($(element).is("coral-select")) {
                Coral.commons.ready(element, function (component) {
                    multiSelectShowHide(element);
                    component.on("change", function () {
                        multiSelectShowHide(element);
                    });
                });
            }
        });
    }

    function multiSelectShowHide(element) {
        let target = $(element).data("cqDialogDropdownShowhideTarget");
        let $target = $(target);
        if (target) {
            $target.each(function () {
                $(this).not(".hide").addClass("hide");
                let filteredElement = [];
                let targetValueList = $(this).data('showhidetargetvalue');
                if (typeof targetValueList !== "undefined") {
                    targetValueList.replace(/ /g, '').split(',').forEach((targetValue) => {
                        let idfSelect = $(this).attr("class").split(' ').filter((item) => item !== "hide")
                        let selectedValues = $(this).parent().find("coral-select[data-cq-dialog-dropdown-showhide-target='." + idfSelect + "']")[0].values;
                        if (selectedValues.includes(targetValue)) {
                            filteredElement.push($(this));
                        }
                    });
                    filteredElement.forEach(element => element.removeClass('hide'));
                }
            });
        }
    }
})(document, Granite.$);