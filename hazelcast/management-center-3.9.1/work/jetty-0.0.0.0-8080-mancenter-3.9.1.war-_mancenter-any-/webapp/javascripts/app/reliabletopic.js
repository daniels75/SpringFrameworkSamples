// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function reliabletopicInit() {
refreshpage(0);
}

//newdesign
function dataTablesReliableTopic(data) {
    fillOperationsDataTable('#reliabletopicopstats', data);
}
function reliabletopiclist(data) {
    instanceList("reliabletopics",data,"reliabletopic")
}
