var clusterShutDownByUser = false
var prevState = ""

// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function clusterstateInit() {
    $("#noMigrationStateDrop").parent().remove();
    if (activeClusterVersion != "3.8") {
        var noMigrationStateDropLink = $("<a/>", {id: "noMigrationStateDrop", href: "#", text: "No Migration"});
        var li = $("<li/>");
        li.append(noMigrationStateDropLink);
        $("#changeClusterStateDropdownMenu").append(li);
    }

    $('#activeStateDrop').off("click").click(function () {
        sendChangeClusterStateOperation("ACTIVE");
        return false;
    });
    $('#frozenStateDrop').off("click").click(function () {
        sendChangeClusterStateOperation("FROZEN");
        return false;
    });
    $('#passiveStateDrop').off("click").click(function () {
        sendChangeClusterStateOperation("PASSIVE");
        return false;
    });
    $('#noMigrationStateDrop').off("click").click(function(){ sendChangeClusterStateOperation("NO_MIGRATION"); return false; });

    refreshpage(0);
}

function getClusterState(data) {
    $("#stateButton").remove();

    var clusterNameContainer = $("#clusterState_clusterNameContainer");
    clusterNameContainer.empty();
    clusterNameContainer.append($('<span>', {text: activeCluster}));

    var stateButton = "";
    if (data === "ACTIVE") {
        if (prevState === "PASSIVE" && clusterShutDownByUser) {
            $('#stateChangeButton').removeAttr("disabled");
            $('#openClusterShutdownButton').removeAttr("disabled");
            $('#forcestartClusterButton').removeAttr("disabled");
        }
        prevState = data;
        stateButton = "<button id=\"stateButton\" type=\"button\" class=\"btn btn-success\">Active<\/button>";
    } else if (data === "FROZEN") {
        prevState = data;
        stateButton = "<button id=\"stateButton\" type=\"button\" class=\"btn btn-info\">Frozen<\/button>";
    } else if (data === "PASSIVE" || data === "No Cluster Data") {
        prevState = "PASSIVE";
        stateButton = "<button id=\"stateButton\" type=\"button\" class=\"btn btn-danger\">Passive<\/button>";
    } else if (data === "IN_TRANSITION") {
        prevState = data;
        stateButton = "<button id=\"stateButton\" type=\"button\" class=\"btn btn-warning\">In Transition<\/button>";
    } else if (data == "NO_MIGRATION") {
        prevState = data
        stateButton = "<button id=\"stateButton\" type=\"button\" class=\"btn btn-warning\">No Migration<\/button>";
    }

    $("#clusterState").append(stateButton);
    $("#button").prop('disabled', true);
}

function sendChangeClusterStateOperation(stateName) {
    var changeClusterStatusDialog = $("#changeClusterStatusDialog");
    changeClusterStatusDialog.dialog('option', 'title', 'Changing Cluster State');
    var data = {operation: "changeClusterState", cluster: activeCluster, newState: stateName};
    changeClusterStatusDialog.dialog("open");
    changeClusterStatusDialog.parent().find(".ui-dialog-titlebar-close").hide();
    $(".dropdown").removeClass("open");
    opcall(data);
}

function sendShutdownClusterOperation() {
    var changeClusterStatusDialog = $("#changeClusterStatusDialog");
    changeClusterStatusDialog.dialog('option', 'title', 'Shutting down the cluster.');
    clusterShutDownByUser = true;
    var data = {operation: "shutdownCluster", cluster: activeCluster};
    $('#stateChangeButton').attr("disabled", true);
    $('#openClusterShutdownButton').attr("disabled", true);
    $('#forcestartClusterButton').attr("disabled", true);
    $("#changeClusterStatusBar").addClass("bar-danger");
    changeClusterStatusDialog.dialog("open");
    changeClusterStatusDialog.parent().find(".ui-dialog-titlebar-close").hide();
    opcall(data);
}

function openClusterShutdownDialog() {
    $("#clusterShutdownDialog").dialog("open");
}

function shutdownCluster(data) {
    $("#changeClusterStatusBar").removeClass("bar-danger");
    $("#changeClusterStatusDialog").dialog("close");
    if (data === "SUCCESS") {
        $.growl.notice({message: "Cluster Shutdown Successful."});
    } else {
        $.growl.error({message: "Cluster Shutdown failed. Reason: " + data});
    }
}

function changeClusterState(data) {
    $("#changeClusterStatusDialog").dialog("close");
    if (data === "SUCCESS") {
        $.growl.notice({message: "Cluster State Updated Successfully."});
    } else {
        $.growl.error({message: "Cluster State Update failed. Reason: " + data});
    }
}
