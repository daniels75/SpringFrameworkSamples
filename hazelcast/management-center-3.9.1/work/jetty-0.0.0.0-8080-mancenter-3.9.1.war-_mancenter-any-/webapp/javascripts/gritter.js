(function () {
    this.Growl = function () {
        function e() {
        }

        return e.info = function (e) {
            return e.class_name = "info", e.title = "<i class='icon-info-sign'></i> " + e.title, $.gritter.add(e)
        }, e.warn = function (e) {
            return e.class_name = "warn", e.title = "<i class='icon-warning-sign'></i> " + e.title, $.gritter.add(e)
        }, e.error = function (e) {
            return e.class_name = "error", e.title = "<i class='icon-exclamation-sign'></i> " + e.title, $.gritter.add(e)
        }, e.success = function (e) {
            return e.class_name = "success", e.title = "<i class='icon-ok-sign'></i> " + e.title, $.gritter.add(e)
        }, e
    }(), $(function () {
        return $.extend($.gritter.options, {position: "top-right"}), $(".growl-info").click(function (e) {
            return e.preventDefault(), Growl.info({title: "This is a notice!", text: "This will fade out after a certain amount of time."})
        }), $(".growl-warn").click(function (e) {
            return e.preventDefault(), Growl.warn({title: "This is a warning!", text: "This will fade out after a certain amount of time."})
        }), $(".growl-error").click(function (e) {
            return e.preventDefault(), Growl.error({title: "This is an error!", text: "This will fade out after a certain amount of time."})
        }), $(".growl-success").click(function (e) {
            return e.preventDefault(), Growl.success({title: "This is a success message!", text: "This will fade out after a certain amount of time."})
        })
    })
}).call(this);