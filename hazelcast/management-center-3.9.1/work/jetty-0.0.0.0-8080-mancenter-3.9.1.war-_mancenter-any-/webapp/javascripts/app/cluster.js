var colors = [
    "235,104,65",
    "237,201,81",
    "27,103,107",
    "81,149,72",
    "204,51,63",
    "0,160,176",
    "136,196,37",
    "78,205,196",
    "192,41,66",
    "196,77,88",
    "105,210,231",
    "250,105,0",
    "186,48,48",
    "77,184,95"
];

var pieChartOptsMany = {
    series: {
        pie: {
            show: true,
            radius: 1,
            label: {
                show: false
            }
        }
    },
    legend: {
        show: false
    },
    grid: {
        hoverable: true,
        clickable: true
    }
}

function partitionspiechart(dataList) {
    for (var i = 0; i < dataList.length; i++) {
        dataList[i].label = escapeHtml(dataList[i].label);
    }

    var placeholder = $("#clusterPartitionPieChart");
    placeholder.unbind();
    $.plot(placeholder, dataList, pieChartOptsMany);

    placeholder.bind('mouseout', function () {
        $("#tooltip").remove();
        $(this).data('previous-post', -1);
    });

    placeholder.bind('plothover', function (event, pos, item) {
        if (item) {
            if ($(this).data('previous-post') != item.seriesIndex) {
                $(this).data('previous-post', item.seriesIndex);
            }
            $("#tooltip").remove();
            var percent = parseFloat(item.series.percent).toFixed(2);
            showTooltip(pos.pageX, pos.pageY, item.series.label + "-> " + percent + " %");
        } else {
            $("#tooltip").remove();
            previousPost = $(this).data('previous-post', -1);
        }
    });
}

function clusterProps(dataList) {
    if (dataList != null) {
        if (dataList["return.hasOngoingMigration"] == "false") {
            $("#migrationWarningRow").hide();
        } else {
            $("#migrationWarningRow").show();
        }
        for (lbl in dataList) {
            if (lbl.split(".")[0] == "return") {
                continue;
            }
            var idm = lbl.split(".")[1];
            $(document.getElementById(idm)).html(dataList[lbl]);
        }
    }
}

function getAllMemberCPULoadAverage(data) {
    var cpuUsageTableSel = $('#CPUUsageTable');
    cpuUsageTableSel.empty();
    cpuUsageTableSel.append("<tr><th>Node</th><th>1min</th><th>5min</th><th>15min</th><th>Utilization(%)</th></tr>");

    var counter = 0;
    for (var member in data) {
        var memberStr = member.split('.').join("").split(':').join("");
        var row = $("<tr>");
        var column1 = $("<td>", {
            style: "cursor: pointer",
            "data-member": member,
            click: function () {
                tabs.newTab(escapeHtml($(this).data("member")), 'member', listIcons['member']);
            }
        });
        var memberStrongText = $("<strong>", {text: member});
        column1.append(memberStrongText);
        var column2 = $("<td>", {text: data[member]["1min"]});
        var column3 = $("<td>", {text: data[member]["5min"]});
        var column4 = $("<td>", {text: data[member]["15min"]});
        var column5 = $("<td>");
        var memberSpan = $("<span>", {id: memberStr});
        column5.append(memberSpan);
        row.append(column1);
        row.append(column2);
        row.append(column3);
        row.append(column4);
        row.append(column5);
        cpuUsageTableSel.append(row);

        $(document.getElementById(memberStr)).sparkline(
            data[member]['memberCpuChart'], {
                width: "150px",
                height: "35px",
                lineColor: "rgb(" + colors[counter % colors.length] + ")",
                fillColor: "rgba(" + colors[counter % colors.length] + ",0.5)",
                lineWidth: 2,
                spotRadius: 0,
                type: 'line',
                chartRangeMin: 0,
                chartRangeMax: 100,
                tooltipFormatter: function (sparkline, options, point) {
                    var d = new Date(point.x);
                    return d.toLocaleTimeString('en-GB') + "-> " + point.y + "%";
                }
            });
        counter += 1;
    }

    var cpuUsageTableSelTd = cpuUsageTableSel.find('td');
    cpuUsageTableSelTd.css({"vertical-align": "middle"});
    cpuUsageTableSelTd.css({"text-align": "center"});
    cpuUsageTableSel.find('th').css({"text-align": "center"});
}

function getAllMemberMemoryAverage(data) {
    var memoryUsageTableSel = $('#memoryUsageTable')
    memoryUsageTableSel.empty();
    memoryUsageTableSel.append("<tr><th>Node</th><th>Used Heap</th><th>Total Heap</th><th>Max. Heap</th>" +
        "<th>Heap Usage Percentage</th><th>Used Heap (MB)</th>" +
        "<th>Native Memory Max</th><th>Native Memory Used</th><th>Native Memory Free</th><th>Used Native (MB)</th>" +
        "<th>GC Major Count</th><th>GC Major Time(ms)</th><th>GC Minor Count</th><th>GC Minor Time(ms)</th></tr>");

    var counter = 0;
    for (var member in data) {
        var memberStr = member.split('.').join("").split(':').join("") + "memory";

        var row = $("<tr>");

        var nodeColumn = $("<td>", {
            style: "cursor: pointer",
            "data-member": member,
            click: function () {
                tabs.newTab(escapeHtml($(this).data("member")), 'member', listIcons['member']);
            }
        });
        nodeColumn.append($("<strong>", {text: member}));
        row.append(nodeColumn);

        row.append($("<td>", {text: data[member]["usedMemory"] + " MB"}));

        row.append($("<td>", {text: data[member]["totalMemory"] + " MB"}));

        row.append($("<td>", {text: data[member]["maxHeap"] + " MB"}));
        row.append($("<td>", {text: data[member]["percent"] + "%"}));

        var heapGraphColumn = $("<td>");
        heapGraphColumn.append($("<span>", {id: "heap" + memberStr}));
        row.append(heapGraphColumn);

        row.append($("<td>", {text: data[member]["nativeMemoryMax"]}));
        row.append($("<td>", {text: data[member]["nativeMemoryUsed"]}));
        row.append($("<td>", {text: data[member]["nativeMemoryFree"]}));

        var nativeMemoryGraphColumn = $("<td>");
        nativeMemoryGraphColumn.append($("<span>", {id: "native" + memberStr}));
        row.append(nativeMemoryGraphColumn);

        row.append($("<td>", {text: data[member]["gcMajorCount"]}));
        row.append($("<td>", {text: data[member]["gcMajorTime"]}));
        row.append($("<td>", {text: data[member]["gcMinorCount"]}));
        row.append($("<td>", {text: data[member]["gcMinorTime"]}));

        memoryUsageTableSel.append(row);

        $(document.getElementById('heap' + memberStr)).sparkline(data[member]['memberMemoryChart'], {
            width: "100px",
            height: "35px",
            lineColor: "rgb(" + colors[counter % colors.length] + ")",
            fillColor: "rgba(" + colors[counter % colors.length] + ",0.5)",
            lineWidth: 2,
            spotRadius: 0,
            type: 'line',
            chartRangeMin: 0,
            chartRangeMax: 100,
            tooltipFormatter: function (sparkline, options, point) {
                var d = new Date(point.x);
                return d.toLocaleTimeString('en-GB') + "-> " + point.y + " MB"
            }
        });

        $(document.getElementById('#native' + memberStr)).sparkline(data[member]['memberNativeMemoryChart'], {
            width: "100px",
            height: "35px",
            lineColor: "rgb(" + colors[counter % colors.length] + ")",
            fillColor: "rgba(" + colors[counter % colors.length] + ",0.5)",
            lineWidth: 2,
            spotRadius: 0,
            type: 'line',
            chartRangeMin: 0,
            chartRangeMax: 100,
            tooltipFormatter: function (sparkline, options, point) {
                var d = new Date(point.x);
                return d.toLocaleTimeString('en-GB') + "-> " + point.y + " MB"
            }
        });
        counter += 1;
    }

    var memoryUsageTableTd = memoryUsageTableSel.find('td');
    memoryUsageTableTd.css({"vertical-align": "middle"});
    memoryUsageTableTd.css({"text-align": "center"});
    memoryUsageTableSel.find('th').css({"text-align": "center"});
}

// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function homeInit() {
    refreshpage(0);
}

function getMapMemoryDistribution(data) {
    var mapMemoryDistributionSel = $('#mapMemoryDistribution');
    mapMemoryDistributionSel.empty();
    var count = 0;
    var total = 0;
    var colorClass = ["", " bar-danger", " bar-warning", " bar-info", " bar-success"];
    for (var key in data) {
        var percent = parseFloat(data[key])
        total+= percent
        if(total>100.0)
            percent=data[key] - (total-100)
        var div = $("<div>", {
            "class": "bar" + colorClass[count % 5],
            "data-toggle": "tooltip",
            "data-title": key + " -> " + percent.toFixed(2) + " %",
            style: "width:" + percent + "%"
        });
        var span = $("<span>", {text: key + " -> " + percent.toFixed(2)});
        div.append(span);
        $('#mapMemoryDistribution').append(div);
        count = count + 1;
    }
    mapMemoryDistributionSel.find(".bar").tooltip();
}

function getMemoryDistribution(data) {
    var memoryDistributionSel = $('#memoryDistribution');
    memoryDistributionSel.empty();
    var colorClass = ["", " bar-warning", " bar-success", " bar-info", " bar-danger"];
    var count = 0;
    var total = 0;

    for (var key in data) {
        var percent = parseFloat(data[key]);
        total+= percent;
        if(total>100.0) {
            percent=data[key] - (total-100);
        }
        var div = $("<div>", {
            "class": "bar" + colorClass[count % 5],
            "data-toggle": "tooltip",
            "data-title": key + " -> " + percent.toFixed(2) + " %",
            style: "width:" + percent + "%"
        });
        var span = $("<span>", {text: key + " -> " + percent.toFixed(2)});
        div.append(span);
        memoryDistributionSel.append(div);
        count = count + 1;
    }
    memoryDistributionSel.find(".bar").tooltip();
}

function getUnsafeMembers(data) {
    var healthCheckTbodySel = $('#healthCheckTbody');
    healthCheckTbodySel.empty();

    if (data.length == 0) {
        $('#unsafeMembersDiv').hide();
    } else {
        $('#unsafeMembersDiv').show();
        for (var i = 0; i < data.length; i++) {
            healthCheckTbodySel.append('<tr><td>' + data[i] + '&nbsp;<img src="./images/warning.png" style="height:20px;"/></td></tr>')
        }
    }
}

function getMigrationQueueSize(data) {
    var waitingMigrationQueueSizeSel = $('#waitingMigrationQueueSize');
    waitingMigrationQueueSizeSel.html(data);

    if (data > 0) {
        waitingMigrationQueueSizeSel.closest('div').removeClass('alert-info');
    } else {
        waitingMigrationQueueSizeSel.closest('div').addClass('alert-info');
    }
}

