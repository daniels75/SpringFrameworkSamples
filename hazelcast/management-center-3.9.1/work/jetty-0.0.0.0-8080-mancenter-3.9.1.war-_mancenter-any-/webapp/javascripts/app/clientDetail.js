// don't delete, this method is called dynamically by Tabs.prototype.showTab on tab.js
function clientDetailInit() {
    refreshpage(0)

    $('#clientInfoTabs li').removeClass('active')
    $('#clientInfoTabs a[href="#clientOs"]').tab('show')
    $('#clientInfoTabs a[href="#clientRuntime"]').tab('show')
    $('#clientInfoTabs a[href="#clientGeneralInfo"]').tab('show')
}

function clientDetails(data) {
    drawChart("clientMemoryChart", "clientDetail", data.clientMemoryChart, true)

    $("#clientGeneralInfoName").text(data.generalInfo.name)
    $("#clientGeneralInfoAddress").text(data.generalInfo.address)
    $("#clientGeneralInfoType").text(data.generalInfo.type)
    $("#clientGeneralInfoEnterprise").text(data.generalInfo.enterprise)
    $("#clientGeneralInfoMemberConnection").text(data.generalInfo.memberConnection)
    $("#clientGeneralInfoVersion").text(data.generalInfo.version)
    $("#lastConnectionToCluster").text(data.generalInfo.lastConnectionToCluster)
    $("#lastStatisticsCollection").text(data.generalInfo.lastStatisticsCollection)
    $("#userExecutorQueueSize").text(data.generalInfo.userExecutorQueueSize)
    $("#clientAvailableProcessors").text(data.runtimeStats.availableProcessors)
    $("#clientUpTime").text(data.runtimeStats.uptime)
    $("#clientMaxMemory").text(data.runtimeStats.maxMemory)
    $("#clientTotalMemory").text(data.runtimeStats.totalMemory)
    $("#clientFreeMemory").text(data.runtimeStats.freeMemory)
    $("#clientMemoryUsed").text(data.runtimeStats.usedMemory)

    $("#clientFreePhysicalMemory").text(data.osStats.freePhysicalMemorySize)
    $("#clientCommittedVirtualMemory").text(data.osStats.committedVirtualMemorySize)
    $("#clientTotalPhysicalMemory").text(data.osStats.totalPhysicalMemorySize)
    $("#clientFreeSwapSpace").text(data.osStats.freeSwapSpaceSize)
    $("#clientTotalSwapSpace").text(data.osStats.totalSwapSpaceSize)
    $("#clientMaxFileDescriptorCount").text(data.osStats.maxFileDescriptorCount)
    $("#clientOpenFileDescriptorCount").text(data.osStats.openFileDescriptorCount)
    $("#clientProcessCpuTime").text(data.osStats.processCpuTime)
    $("#clientSystemLoadAverage").text(data.osStats.systemLoadAverage)

    fillDataTable('#clientNearCacheTableForMaps', data.nearCacheTableDataForMaps)
    fillDataTable('#clientNearCacheTableForCaches', data.nearCacheTableDataForCaches)

    $("#clientNearCacheForMapsDiv").show()
    $("#clientNearCacheForCachesDiv").show()
}