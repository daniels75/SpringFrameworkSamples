
function clusterConnect() {
    activeCluster = $("[name='clusterradiolist']:checked").val();
    document.title = activeCluster + " | Management Center";
    $('#activeClusterName').text(activeCluster);
    $(".login-div").dialog('close');
    tabs.newTab('Home','home','icon-home');
    refreshpage(0);

}

function clusternames(user) {
    admin = user.admin;
    var clusterListSel = $('#clusterList');
    clusterListSel.empty();
    clusterList = [];
    var nocluster = true;
    var clusterNames = user.clusterNames;
    for (var i = 0; i < clusterNames.length ; i++) {
        var cluster = clusterNames[i];
        var label = $("<label>", {
            "class": "radio"
        });
        var input = $("<input>", {
            id: "clusterradiolist" + i,
            type: "radio",
            name: "clusterradiolist",
            value: cluster
        });
        var badgeSpan = $("<span>", {
            "class": "badge badge-inverse",
            text: cluster
        });
        label.append(input);
        label.append(badgeSpan);
        clusterListSel.append(label);

        clusterList.push(cluster);
        var li = $("<li>");
        var switchClusterLink = $("<a>", {onclick: "switchCluster(\""+ cluster+"\")"});
            // .click(function() {
            // switchCluster(cluster);
        // });
        var icon = $("<i>", {
            "class": "icon-caret-right"
        });
        var span = $("<span>", {
            text: cluster
        });
        switchClusterLink.append(icon);
        switchClusterLink.append(span);
        li.append(switchClusterLink);
        $('#clusterDropdownList').append(li);
        nocluster = false;
    }

    $("[name='clusterradiolist']:first").attr("checked", true)


    $("#loginModal").modal('hide')

    if (user.licenseInfo == null || user.licenseInfo == 'EXPIRED') {
        $("#applyWarnDiv").show()
        if (user.licenseInfo == null)
            $("#licenseInfo").html("Developer Mode: Limited to 2 nodes")
        else if (user.licenseInfo == 'EXPIRED'){
            $("#licenseInfo").html("Your license has been expired. You can continue in developer Mode (Limited to 2 nodes) or renew your license.")
        }
        if($('#enterLicenseButton').size()==0)
        {
            var buttons = $( "#clusterConnectDialog" ).dialog( "option", "buttons");
            $( "#clusterConnectDialog" ).dialog("option", "buttons", [buttons[0], {id:"enterLicenseButton", text:"Enter License",click:function () {
                showEnterLicense()
            }}]);
        }
    }
    else {
        $("#licenseInfo").html(user.licenseInfo)
    }

    refreshpage(refreshSeconds);
    if(nocluster == true){
        $("#noDataDialog").dialog( "open" );
    }else {
        $('#clusterConnectDialog').dialog('open')
        $("#clusterConnectButton").focus()
    }
}


function changeUrlAction() {
    var data = {
        operation: "changeurl",
        clusterName: $("#clusterNameInput").val(),
        password: $("#clusterPasswordInput").val(),
        memberIP: $("#memberIPInput").val(),
        memberPort: $("#memberPortInput").val(),
        serverUrl: $("#serverUrlInput").val(),
        sslEnabled: $("#sslEnabledCheckbox").is(':checked')
    }
    console.log(data)
    opcall(data)
}


function changeurl(data) {
    if (data == "wrongpass") {
        $("#changeUrlMessage").html("Wrong cluster name/password.")
    } else if (data == "success") {
        $("#changeUrlMessage").html("Successfully assigned new URL.")
        $(".login-div").dialog('close')
        getVersionAction(); // re - init
    } else if (data == "notallowed") {
        $("#changeUrlMessage").html("URL change is disabled. To enable set system property 'hazelcast.mc.url.change.enabled' to true")
    } else {
        $("#changeUrlMessage").html("Problem occured. Check member IP/Port and be sure the member is running. " +
            "Remember to check SSL box if SSL is enabled for your cluster.")
    }
}

function sendChangeClusterVersionOperation(version) {
    var cluster = $("#clusterVerNameInput").val();
    var password = $("#clusterVerPasswordInput").val();
    var newClusterVersion = $("#changeClusterVersionInput").val();

    if (!cluster || cluster.trim().length === 0) {
        $.growl.error({message: "Please enter cluster name."});
        return;
    }

    if (!password || password.trim().length === 0) {
        $.growl.error({message: "Please enter password."});
        return;
    }

    if (!newClusterVersion || newClusterVersion.trim().length === 0) {
        $.growl.error({message: "Please enter new version."});
        return;
    }

    var newClusterVersionFloat = parseFloat(newClusterVersion);
    var currentVersionFloat = parseFloat(version);
    if(newClusterVersionFloat <= currentVersionFloat){
        $.growl.error({message: "Please enter version(major.minor) released after " + version});
        return;
    }

    opcall({
        cluster: cluster,
        password: password,
        version: newClusterVersion,
        operation: "changeClusterVersion"
    });
}

function changeClusterVersion(result) {
    if (result === 'success') {
        $.growl.notice({message: "Cluster version has been successfully changed."});
        $("#changeVersionDialog").dialog("close");
    } else {
        $.growl.error({message: "Failed to change cluster version: " + result});
    }
}

function reloadSecurityConfig() {
    var request = $.ajax({
        url: "user.do",
        data: {operation:"reloadSecurityConfig"},
        cache: false,
        dataType: "json"
    });

    request.done(function (resp) {
       for (key in resp) {
           if (key == "fail") {
               $.growl.error({message: resp[key]})
           } else if (key == "success") {
               $.growl.notice({message: resp[key]})
           }
       }
    });
}
