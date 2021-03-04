// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function multimapInit() {
refreshpage(0);
}
//newdesign
function dataTablesMultiMap(data) {
    fillDataTable('#multimapmemorydatatable', data.fillMultiMapMemoryTable);
    fillOperationsDataTable('#multimapthroughputdatatable', data.fillMultiMapThroughputTable);
}
function multimaplist(data) {
    multimapListCache = data
    instanceList("multimaps", data, "multimap")
}

