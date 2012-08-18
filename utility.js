function makeEnterClickDefaultButton() {
    $("input").bind("keydown", function(e) {
        if ((e.which == 13) && (e.target.type != 'textarea')) {
            var defaultButtons = $(".defaultButton");
            if (defaultButtons.length == 1) {
                if ((defaultButtons[0].type == "button") || (defaultButtons[0].type == "submit")) {
                    defaultButtons[0].click();
                } else {
                    eval(defaultButtons[0].href);
                }
                return false;
            } else  {
                return true;
            }
        }
    });
}