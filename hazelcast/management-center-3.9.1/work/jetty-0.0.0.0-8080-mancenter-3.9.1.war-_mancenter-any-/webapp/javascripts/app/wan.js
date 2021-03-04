var schemeCache = ''
var lastTables = {};
var wanConfig = '';
var publisherConfig = '';
var syncMap = '';

function wanrepInit() {
    getPublisherList(activeObject);
}

function wanreptopInit() {
    $('#publishersDropdown').on("change", function () {
        publisherConfig = this.value;
    });

    $('#mapsDropdown').on("change", function () {
        syncMap = this.value;
    });
}

function getPublisherList(schemeName) {
    schemeCache = schemeName;
    opcall({cluster: activeCluster, scheme: schemeName, operation: "instancelist_wanPublisherList", curtime: 0});
}

function openWanConfigDialog() {
    $('#wanConfigDialog').dialog("open");
}

function openWanSyncDialog() {
    $('#wanSyncDialog').dialog("open");
}

//called by wanConfigButton button
function sendAddWanConfigOperation() {
    var wanConfigForm = $("#wanConfigForm");
    var data = {cluster: activeCluster, operation: "addWanConfig"};

    $('.wanconfiginfo').each(
        function () {
            var title = $(this).attr("title");
            data[title] = $(this).val();
            //get placeholder values for default configs
            data["ph-" + title] = $(this).attr("placeholder");
        });

    var configName = wanConfigForm.find('input[title="configName"]');
    var publisherName = wanConfigForm.find('input[title="publisherName"]');
    var endpoints = wanConfigForm.find('input[title="endpoints"]');
    var groupPassword = wanConfigForm.find('input[title="groupPassword"]');
    if ($.trim(configName.val()) === "" || $.trim(publisherName.val()) === ""
        || $.trim(endpoints.val()) === "" || $.trim(groupPassword.val()) === "") {
        $("#formAlert").slideDown(400);
    } else {
        opcall(data);
    }

    $(".alert").find(".close").on("click", function (e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).closest(".alert").slideUp(400);
    });
}

//called by webservice
function addWanConfig(dlist) {
    $("#wanConfigDialog").dialog("close");
    if (dlist === 'success') {
        $.growl.notice({message: "Configuration has been successfully added."});
    } else {
        $.growl.error({message: "There is a problem with adding wan configuration."});
        $.growl.error({message: dlist});
    }
}

function wanPublisherList(data) {
    for (var i = 0; i < data.length; i++) {
        if (!lastTables[data[i]]) {
            lastTables[data[i]] = true;
            $('#wanContainer').append(generateWanTable(data[i]));
        }
        fillWanDataTable(data[i]);
    }

    for (var key in lastTables) {
        if (data.indexOf(key) === -1) {
            $("div[id='" + key + "WanDiv']").remove();
            delete lastTables[key];
        }
    }
}

function wanlist(data) {
    instanceList("wanreps", data, "wanrep");
}

function getWanSyncState(data) {
    var wan = data["wanconfigName"];
    var publisher = data["publisherName"];

    var status = data["status"];
    if (status === "IN_PROGRESS") {
        $("#startWanSyncButton").prop("disabled", true);
    } else {
        $("#startWanSyncButton").prop("disabled", false);
    }

    var progress = data["progress"];
    var $progressBar = $('<div/>').attr('class', 'progress progress-striped');
    var $div = $('<div class="bar" style="width: ' + progress + '%;"></div>');
    $progressBar.append($div);

    if (wan !== null && wan !== undefined && publisher !== null && publisher !== undefined) {
        var confNameColumn = $('<td>', {"class": "span3", text: wan});
        var publisherNameColumn = $('<td>', {"class": "span3", text: publisher});
        var progressBarColumn = $('<td>', {"class": "span3"});
        progressBarColumn.append($progressBar);
        var statusColumn = $('<td>', {"class": "span3", text: status});

        var row = $('<tr>')
            .append(confNameColumn)
            .append(publisherNameColumn)
            .append(progressBarColumn)
            .append(statusColumn);

        $('#wanTargetTable').find('tbody tr').replaceWith(row);
    }
}

function wanConfigList(data) {
    var wanConfigs = $('#wanConfigsDropdown');
    wanConfigs.empty();
    wanConfigs.append($('<option selected disabled></option>').val("").text("Select WAN Configuration"));
    for (var wan in data) {
        if (data.hasOwnProperty(wan)) {
            var optionElem = $('<option>').val(wan).text(wan);
            if (wan === wanConfig) {
                optionElem.attr('selected', 'selected');
            }
            wanConfigs.append(optionElem);
        }
    }
    wanConfigs.off("change");
    wanConfigs.on("change", function () {
        wanConfig = this.value;
        fillWanPublishersDropdown(data, wanConfig);
    });
}

function fillWanPublishersDropdown(data, wanConfigName) {
    var publisherConfigs = $('#publishersDropdown');
    publisherConfigs.empty();
    publisherConfigs.append($('<option selected disabled></option>').val("").text("Select Publisher"));

    var publishers = data[wanConfigName];
    var publisher;
    for (var i in publishers) {
        publisher = publishers[i];
        var optionElem = $('<option>').val(publisher).text(publisher);
        if (publisher === publisherConfig) {
            optionElem.attr('selected', 'selected');
        }
        publisherConfigs.append(optionElem);
    }
}

function mapListForWanSync(data) {
    var mapsDropdown = $('#mapsDropdown');
    mapsDropdown.empty();
    mapsDropdown.append($('<option selected disabled></option>').val("").text("Select Map"));

    for (var i = 0; i < data.length; i++) {
        var map = data[i];
        var optionElem = $('<option>').val(map).text(map);
        if (map === syncMap) {
            optionElem.attr('selected', 'selected');
        }
        mapsDropdown.append(optionElem);
    }
    mapsDropdown.append($('<option>').val("allmaps").text("All Maps"));
}

//called by syncButton
function sendWanSyncMapOperation() {
    var data;
    if (syncMap === "allmaps") {
        data = {
            cluster: activeCluster,
            curtime: 0,
            wanRep: wanConfig,
            publisher: publisherConfig,
            operation: "wanSyncAllMaps"
        };
    } else {
        data = {
            cluster: activeCluster,
            curtime: 0,
            wanRep: wanConfig,
            publisher: publisherConfig,
            syncMap: syncMap,
            operation: "wanSyncMap"
        };
    }
    opcall(data);
    clearWanSyncSelection();
}

function clearWanSyncSelection() {
    wanConfig = '';
    publisherConfig = '';
    syncMap = '';

    $('#wanConfigsDropdown').val('');
    $('#publishersDropdown').val('');
    $('#mapsDropdown').val('');
}

//called by webservice
function wanSyncMap(dlist) {
    $('#wanSyncDialog').dialog("close");
    if (dlist === 'success')
        $.growl.notice({message: "Sync initiated"});
    else {
        $.growl.error({message: "Can not be initiated"});
        $.growl.error({message: dlist});
    }
}

function wanSyncAllMaps(dlist) {
    $('#wanSyncDialog').dialog("close");
    if (dlist === 'success')
        $.growl.notice({message: "All Map Sync initiated"});
    else {
        $.growl.error({message: "Can not be initiated"});
        $.growl.error({message: dlist});
    }
}

function fillWanDataTable(publisherName) {
    opcall({
        cluster: activeCluster,
        curtime: 0,
        scheme: schemeCache,
        publisher: publisherName,
        operation: "wanPublisherDataTable"
    });
}

function wanPublisherDataTable(data) {
    var name = data[data.length - 1][0];
    data.splice(data.length - 1);
    fillDataTable("table[id='" + name + "WanTable']", data);
}

function stopMember(member, scheme, publisher) {
    opcall({
        operation: 'stopWanMember',
        cluster: activeCluster,
        member: member,
        scheme: scheme,
        publisher: publisher
    });
}

function startMember(member, scheme, publisher) {
    opcall({
        operation: 'startWanMember',
        cluster: activeCluster,
        member: member,
        scheme: scheme,
        publisher: publisher
    });
}

function startWanMember(data) {
    $.growl.warning({message: "Starting WAN Replication request is sent to member."});
}

function stopWanMember(data) {
    $.growl.warning({message: "Stopping WAN Replication request is sent to member."});
}

function clearWanQueues(data) {
    $.growl.warning({message: "Request is sent to member"});
}

function generateWanTable(name) {
    var wanTableHtml = "";
    wanTableHtml += "<div class=\"row-fluid\" id=\"" + name + "WanDiv\">";
    wanTableHtml += "    <div class=\"span12\">";
    wanTableHtml += "        <div class=\"box\">";
    wanTableHtml += "            <div class=\"box-header\">";
    wanTableHtml += "                <span class=\"title\">" + name + "<\/span>";
    wanTableHtml += "            <\/div>";
    wanTableHtml += "            <div class=\"box-content\">";
    wanTableHtml += "                <table class=\"dTable responsive dataTable\" id=\"" + name + "WanTable\">";
    wanTableHtml += "                    <thead>";
    wanTableHtml += "                    <tr>";
    wanTableHtml += "                        <th class=\"sorting\">";
    wanTableHtml += "                            <div><span>#&nbsp;&nbsp;<\/span><\/div>";
    wanTableHtml += "                        <\/th>";
    wanTableHtml += "                        <th class=\"sorting\">";
    wanTableHtml += "                            <div>Members<\/div>";
    wanTableHtml += "                        <\/th>";
    wanTableHtml += "                        <th class=\"sorting\">";
    wanTableHtml += "                            <div>Connected<\/div>";
    wanTableHtml += "                        <\/th>";
    wanTableHtml += "                        <th class=\"sorting\">";
    wanTableHtml += "                            <div>Outbound Recs (Sec)<\/div>";
    wanTableHtml += "                        <\/th>";
    wanTableHtml += "                        <th class=\"sorting\">";
    wanTableHtml += "                            <div>Outbound Lat (ms)<\/div>";
    wanTableHtml += "                        <\/th>";
    wanTableHtml += "                        <th class=\"sorting\">";
    wanTableHtml += "                            <div>Outbound Queue<\/div>";
    wanTableHtml += "                        <\/th>";
    wanTableHtml += "                        <th>";
    wanTableHtml += "                            <div>Action<\/div>";
    wanTableHtml += "                        <\/th>";
    wanTableHtml += "                    <\/tr>";
    wanTableHtml += "                    <\/thead>";
    wanTableHtml += "                <\/table>";
    wanTableHtml += "            <\/div>";
    wanTableHtml += "        <\/div>";
    wanTableHtml += "    <\/div>";
    wanTableHtml += "<\/div>";

    return wanTableHtml
}
