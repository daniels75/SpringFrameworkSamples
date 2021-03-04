// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function clientInit() {
    refreshpage(0)
}

function clientData(data) {
    var clientDataTable = fillDataTable('#clientTable', data)
    $('#clientTable tbody').on( 'click', 'tr', function () {
        var uuid = $(this).children(":last").text()

        if (uuid) {
            tabs.newTab(uuid, "clientDetail", listIcons["clientDetail"])
        }
    } );
}

function getNumberOfClients(data) {
    $("#clientSidebar").html(data)
    $("#clientTableTitleNumber").html(data)
}