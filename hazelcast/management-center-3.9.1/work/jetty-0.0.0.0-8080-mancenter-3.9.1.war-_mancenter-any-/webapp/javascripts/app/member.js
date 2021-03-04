var memberTobeKilled = "";
// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function memberInit() {
    $('#memberInfoTabs li').removeClass('active')
    $('#memberInfoTabs a[href="#runtime"]').tab('show')
    sendGetMemberVersionOperation()
    sendCheckLiteMemberOperation()
    refreshpage(0);
}

function checkLiteMember(data) {
    if (data == true) {
        $("#promoteMemberButton").show()
    } else {
        $("#promoteMemberButton").hide()
    }
}

function enableMemberButtons() {
    disableWriteModeButtons();
}

function sendGC() {
    $("#runGcButton").addClass("active");
    var data = {cluster: activeCluster, operation: "runGC", member: activeObject};
    opcall(data);
}

function runGC(data) {
    $.growl.notice({title: "Garbage Collection!", message: data});
    $("#runGcButton").removeClass("active");
}

function getThreadDump() {
    $("#threadDumpButton").addClass("active");
    var data = {cluster: activeCluster, operation: "threaddump", member: activeObject};
    opcall(data);
}

function threaddump(data) {
    $('#threadDumpDialog').dialog("open");
    var threadDumpModalBodySel = $("#threadDumpModalBody");
    threadDumpModalBodySel.val(data);
    $("#threadDumpButton").removeClass("active");
}

function memberShutdownAction() {
    $("#shutdownNodeButton").addClass("active");
    $("#nodeShutdownDialog").dialog("open");
}

function promoteMemberAction() {
    $("#promoteMemberButton").addClass("active")
    $("#promoteMemberDialog").dialog("open")
}

function sendPromoteMemberOperation() {
    var data = {
        operation: "promoteMember",
        cluster: activeCluster,
        member: activeObject
    }

    opcall(data)
}

function promoteMember(data) {
    if (data.success) {
        $.growl.notice({title: "Member promotion", message: "Member promotion request successfully sent."})
        $("#promoteMemberButton").hide()
    } else {
        $.growl.error({title: "Failed to promote member.", message: "Reason: " + data.message})
    }
    $("#promoteMemberButton").removeClass("active")
}

function memberShutdown(olist) {
    var shutdownNodeButtonSel = $("#shutdownNodeButton");
    if (olist === "success") {
        $(".writeModeButton").button("option", "disabled", false);
        $.growl.notice({title: "Member Shutdown!", message: "Member shutdown request successfully sent"});
        tabs.closeTab(tabs.getTabByLabel(memberTobeKilled).getId());
        shutdownNodeButtonSel.removeClass("active");
        shutdownNodeButtonSel.removeAttr("disabled");
        memberTobeKilled = "";
    } else {
        $.growl.error({title: "Member Shutdown!", message: "Member shutdown failed. Reason: " + olist})
        shutdownNodeButtonSel.removeClass("active");
        shutdownNodeButtonSel.removeAttr("disabled");
    }
}

var initMemberListForScripting = true;

function fillMemberListForScripting(data) {
    if (data == null) {
        return;
    }
    var isSame = true;

    if (data.length != memberListCache.length) {
        isSame = false;
    } else {
        for (var i = 0; i < data.length; i++) {
            if (data[i]["id"] != memberListCache[i]["id"]) {
                isSame = false;
                break;
            }
        }
    }

    if (!isSame || initMemberListForScripting) {
        var memberCheckListSel = $('#memberCheckList');
        memberCheckListSel.empty();
        for (var j = 0; j < data.length; j++) {
            memberCheckListSel.append("<label for='memberchecklist" + j
                + "' class='checkbox'><div class='memberCheckDiv' ><input id='memberchecklist" + j
                + "' checked='checked' type='checkbox' name='memberchecklist' value='" + data[j].label + "'  />"
                + data[j].label + "</label></div>");
        }
        initMemberListForScripting = false;
    }
}

function fillMemberCheckListForLogs() {
    var memberCheckListForLogsSel = $('#memberCheckListForLogs');
    memberCheckListForLogsSel.empty();
    for (var i = 0; i < memberListCache.length; i++) {
        memberCheckListForLogsSel.append("<div class='memberCheckDiv' ><input id='memberchecklistForLogs" + i
            + "' checked='checked' type='checkbox' name='memberchecklistForLogs' value='" + memberListCache[i].label
            + "'  /><label for='memberchecklistForLogs" + i + "' class='itemLink'>" + memberListCache[i].label
            + "</label></div>");
    }
}

function memberpartitions(numberOfPartitions) {
    $("#memberPartitions").html(numberOfPartitions);
}

function sendCheckLiteMemberOperation() {
    var data = {
        operation: "checkLiteMember",
        cluster: activeCluster,
        member: activeObject
    }
    opcall(data)
}

//when rolling upgrade is in progress.
function sendGetMemberVersionOperation() {
    var data = {
        cluster: activeCluster,
        operation: "getMemberVersion",
        member: activeObject
    };
    opcall(data);
}

function getMemberVersion(version) {
    if(data === "No Cluster Data") {
        return;
    }
    $("#memberCurrVersion").html(version);
}

function memberProps(dataList) {
    var memberPropsSel = $("#memberProps");
    memberPropsSel.empty();

    if (dataList !== null) {
        for (var lbl in dataList) {
            if (dataList.hasOwnProperty(lbl)) {
                var tr = $("<tr>");
                var td1 = $("<td>").append($("<strong>", {text: lbl + ":"}));
                var td2 = $("<td>", {"class": "valueTd valueTdSmall", text: dataList[lbl]});
                tr.append(td1);
                tr.append(td2);
                memberPropsSel.append(tr);
            }
        }
    }
}

function memberConfig(dataList) {
    var memberConfigSel = $("#memberConfig");
    memberConfigSel.empty();

    if (dataList !== null) {
        var pre = $("<pre>", {id: "memberConfigCode"});
        var code = $("<code>", {"class": "xml", style: "font-size: 9pt;", text: dataList});
        pre.append(code);
        memberConfigSel.append(pre);

        $('#memberConfigCode').each(function (i, e) {
            hljs.highlightBlock(e, '  ');
        });
    }
}

function memberruntime(dataList) {
    if (dataList !== null) {
        for (var lbl in dataList) {
            if (dataList.hasOwnProperty(lbl)) {
                var idm = lbl.split(".")[1];
                $("#" + idm).text(dataList[lbl]);
            }
        }
    }
}

function memberlist(olist) {
    var membersSel = $('#members');
    membersSel.empty();
    $("#memberSidebar").text(olist.length);
    if (olist == null || olist.length == 0) {
        membersSel.append("<div style='height: 1px'></div>");
        return;
    }
    membersSel.append("<div style='height: 1px'></div>");
    for (var i = 0; i < olist.length; i++) {
        var latencyPic = "";
        var info = "Latest Data: " + olist[i].latency + " seconds ago";
        if (olist[i].latency == -1) {
            latencyPic = "icon-circle";
        } else if (olist[i].latency < 60) {
            latencyPic = "icon-circle-arrow-up";
        } else if (olist[i].latency < 300) {
            latencyPic = "icon-circle-arrow-right";
        } else {
            latencyPic = "icon-circle-arrow-down text-error";
        }

        var memberSidebarItem = $("<li>");
        var memberSidebarLink = $("<a>", {
            href: "#",
            onclick: "tabs.newTab(\"" + escapeHtml(olist[i].label) + "\", \"member\", \"" + listIcons['member'] + "\");",
            text: olist[i].label
        });
        var memberSidebarIcon = $("<i>", {
            "class": latencyPic
        });
        memberSidebarLink.prepend(memberSidebarIcon);
        memberSidebarItem.append(memberSidebarLink);
        membersSel.append(memberSidebarItem);
    }
    if (tabs.getCurrentTab() != null) {
        if (tabs._currentTab.getLabel() == "Scripting") {
            fillMemberListForScripting(olist);
        }
    }
    memberListCache = olist;
}

function getMemberCPUChart(data) {
    drawChart("memberCPUChart", "member", data);
}

function getMemberMemoryChart(data) {
    drawChart("memberMemoryChart", "member", data, true);
}

function getMemberNativeMemoryChart(data) {
    drawChart("memberNativeMemoryChart", "member", data, true);
}

function getMemberSlowOperationsList(data) {
    var slowOperationListSel = $("#slowOperationList");
    var slowOperationPlaceholderSel = $("#slowOperationPlaceholder");

    if (!data || data.length === 0) {
        slowOperationListSel.hide();
        slowOperationPlaceholderSel.show();
        return;
    }
    var transformedData = [];
    for (var i = 0; i < data.length; i++) {
        var arrData = [];
        arrData.push(data[i].operation || "");
        if (data[i].stackTrace) {
            var shortStackTrace = data[i].stackTrace.substring(0, data[i].stackTrace.indexOf('\n', 100)) + ", (...)";
            arrData.push(shortStackTrace.replace(/\n\t/g, ', at '));
        } else {
            arrData.push("No stacktrace available...");
        }
        arrData.push(data[i].totalInvocations || "");
        arrData.push(data[i].invocations || "");
        // we are also keeping long version of stack trace
        arrData.push(data[i].stackTrace.replace(/\n\t/g, '<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	at ') || "");

        transformedData.push(arrData);
    }
    fillSlowOperationsDataTable('#slowOperationsTable', transformedData);
    slowOperationPlaceholderSel.hide();
    slowOperationListSel.show();
}
