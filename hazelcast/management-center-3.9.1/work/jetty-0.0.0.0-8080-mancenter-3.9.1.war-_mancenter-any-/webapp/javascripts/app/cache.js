// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function cacheInit() {
}

function cachelist(data) {
    instanceList("caches", data, "cache");
}

function dataTablesCache(data) {
    fillDataTable('#cachedatatable', data.fillCacheTable);
    fillOperationsDataTable('#cachethroughputdatatable', data.fillCacheThroughputTable);
}

function cacheBrowseModal() {
    $('#cacheBrowseDialog').dialog("open");
}

function cacheBrowse() {
    var cacheBrowseButtonSel = $("#cacheBrowseButton");
    cacheBrowseButtonSel.addClass("active");

    var type = $("#cacheBrowseKeyType").val();
    var browseKey = $("#cacheBrowseKey").val();
    if (isEmpty(browseKey)) {
        $.growl.error({message: "Key needs to be non-empty!"});
        cacheBrowseButtonSel.removeClass("active");
        return;
    }
    if ((type === "integer" || type === "long") && !isAllDigits(browseKey)) {
        $.growl.error({message: "Key needs to be all digits!"});
        cacheBrowseButtonSel.removeClass("active");
        return;
    }

    var data = {
        cluster: activeCluster,
        operation: "browseCache",
        cacheName: activeObject,
        key: browseKey,
        type: type
    };
    opcall(data);
}

//called by webservice
function browseCache(data) {
    var cacheBrowseInfoSel = $("#cacheBrowseInfo");
    var cacheBrowseButtonSel = $("#cacheBrowseButton");

    cacheBrowseInfoSel.text("");
    if (Object.keys(data).length === 0) {
        $(".browseValueTd").text("");
        cacheBrowseInfoSel.text("No value found.");
        cacheBrowseButtonSel.removeClass("active");
        return;
    }
    for (var info in data) {
        if (data.hasOwnProperty(info)) {
            $("#" + info).text(data[info]);
        }
    }
    cacheBrowseButtonSel.removeClass("active");
}