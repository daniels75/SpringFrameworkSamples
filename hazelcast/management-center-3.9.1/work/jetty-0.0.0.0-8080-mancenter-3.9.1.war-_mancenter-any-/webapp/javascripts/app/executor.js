// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function executorInit() {
refreshpage(0);
}
//newdesign
function executorlist(data) {
    executorListCache = data
    instanceList("executors",data,"executor")
}
function dataTablesExecutor(data) {
    fillOperationsDataTable('#executoropstats', data);
}
