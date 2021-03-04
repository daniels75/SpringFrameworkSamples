// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function topicInit() {
refreshpage(0);
}

//newdesign
function dataTablesTopic(data) {
    fillOperationsDataTable('#topicopstats', data);
}
function topiclist(data) {
    instanceList("topics",data,"topic")
}
