// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function replicatedmapInit() {
    //enableMapButtons()
    //refreshpage(0);
}

function replicatedmaplist(data) {
    replicatedMapListCache = data
    instanceList("replicatedmaps",data,"replicatedmap")
}


function openChartOpts(e, did) {
    $(did).dialog("open").dialog("option", "position", [e.pageX, e.pageY]);
}



function memberFormatter(row, cell, value, columnDef, dataContext) {
    if (value == "TOTAL") {
        return "<a>" + value + "</a>";
    }
    return "<a href='#' onclick='addViewShort(\"" + value + "\", \"member\")'>" + value + "</a>";
}

function dataTablesReplicatedMap(data) {
    fillDataTable('#replicatedmapmemorydatatable', data.fillReplicatedMapMemoryTable)
    fillOperationsDataTable('#replicatedmapthroughputdatatable', data.fillReplicatedMapThroughputTable)
}
