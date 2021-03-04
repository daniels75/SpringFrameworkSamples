// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function mapInit() {
    refreshpage(0);
    var data = {cluster: activeCluster, operation: "loadmapconfig", mapName: activeObject};
    opcall(data);
}

function dataTablesMap(data) {
    fillDataTable('#mapmemorydatatable', data.fillMapMemoryTable);
    fillOperationsDataTable('#mapthroughputdatatable', data.fillMapThroughputTable);
    fillDataTable('#nearcachedatatable', data.fillNearCacheTable);
}

function maplist(data) {
    mapListCache = data;
    instanceList("maps", data, "map");
}

function openChartWindow(activeCluster, activeView, activeObject, chartType) {
    var params = $.param({
        activeCluster: activeCluster,
        activeView: activeView,
        instance: activeObject,
        chartType: chartType
    });
    window.open("chart.html?" + params, "_blank", "width=400px,height=350px,menubar=no,location=no,scrollbars=no,");
}

function mapBrowseModal() {
    $('#mapBrowseDialog').dialog("open");
}

function mapBrowse() {
    var mapBrowseButtonSel = $("#mapBrowseButton");
    mapBrowseButtonSel.addClass("active");

    var type = $("#mapBrowseKeyType").val();
    var browseKey = $("#mapBrowseKey").val();
    if (isEmpty(browseKey)) {
        $.growl.error({message: "Key needs to be non-empty!"});
        mapBrowseButtonSel.removeClass("active");
        return;
    }
    if ((type === "integer" || type === "long") && !isAllDigits(browseKey)) {
        $.growl.error({message: "Key needs to be all digits!"});
        mapBrowseButtonSel.removeClass("active");
        return;
    }
    var data = {
        cluster: activeCluster,
        operation: "browseMap",
        mapName: activeObject,
        key: browseKey,
        type: type
    };
    opcall(data);
}

//called by webservice
function browseMap(dataList) {
    var mapBrowseInfoSel = $("#mapBrowseInfo");
    var mapBrowseButtonSel = $("#mapBrowseButton");

    mapBrowseInfoSel.text("");
    if (Object.keys(dataList).length === 0) {
        $(".browseValueTd").text("");
        mapBrowseInfoSel.text("No value found.");
        mapBrowseButtonSel.removeClass("active");
        return;
    }
    for (var data in dataList) {
        if (dataList.hasOwnProperty(data)) {
            $("#" + data).text(dataList[data]);
        }
    }
    mapBrowseButtonSel.removeClass("active");
}

function mapConfigModal() {
    $('#mapConfigDialog').dialog("open");
    $("#updateConfigMessageTd").empty();
    mapConfig();
}

//called by map config button
function mapConfig() {
    var data = {cluster: activeCluster, operation: "loadmapconfig", mapName: activeObject};
    opcall(data);
}

//called by web service
function loadmapconfig(dlist) {
    $(".mapconfiginfo").each(function () {
        var t = $(this).attr("title");

        if ($(this).is('input')) {
            $(this).val("" + dlist[t]);
        }
        else if ($(this).is('select')) {
            $(this).val("" + dlist[t]);
        }
    });

    $("#mapmemorydatatabletitle").text("Map Memory Data Table (In-Memory Format: " + dlist["memoryFormat"] + ")");
    $("#inMemoryStorageFormat").text(dlist["memoryFormat"]);
}

//called by map config update button
function mapConfigUpdate() {
    $("#mapConfigButton").addClass("active");
    $("#updateConfigMessageTd").html("");

    var data = {cluster: activeCluster, operation: "updatemapconfig", mapName: activeObject}
    $('.mapconfiginfo').each(
        function () {
            data[$(this).attr("title")] = $(this).val();
        });
    opcall(data);
}

//called by webservice
function updatemapconfig(dlist) {
    if (dlist === 'success') {
        $("#updateConfigMessageTd").html("Configuration has been successfully updated.");
    } else {
        $("#updateConfigMessageTd").html("There is a problem with updating map configuration.");
    }
    $("#mapConfigButton").removeClass("active");
}
