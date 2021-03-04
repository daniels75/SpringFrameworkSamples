// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function queueInit() {
refreshpage(0);
}
//newdesign
function queuelist(data) {
    queueListCache = data
    instanceList("queues",data,"queue")
}

function dataTablesQueue(data) {
    fillDataTable('#queuestats', data.fillQueueTable);
    fillOperationsDataTable('#queueopstats', data.fillQueueOperationsTable);
}
