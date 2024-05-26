<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/buildings"></c:url>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>

                <li>
                    <a href="#">UI &amp; Elements</a>
                </li>
                <li class="active">Content Sliders</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>Danh sách tòa nhà
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box" style="font-family: 'Times New Roman', Times, serif;">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm tòa nhà</h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="reload">
                                    <i class="ace-icon fa fa-refresh"></i>
                                </a>

                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form action="/admin/building-list" modelAttribute="modelSearch" method="GET"
                                           id="listForm">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-6">
                                                    <label for="name">Tên tòa nhà</label>
                                                    <form:input class="form-control" path="name"></form:input>
                                                </div>

                                                <div class="col-sm-6">
                                                    <label for="name">Diện tích sàn</label>
                                                    <form:input path="floorArea" class="form-control"></form:input>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-2">
                                                    <label for="name">Quận</label>
                                                    <form:select path="district">
                                                        <form:option value="" label="---Chọn Quận---"></form:option>
                                                        <form:options items="${district}"/>
                                                    </form:select>
                                                </div>

                                                <div class="col-sm-5">
                                                    <label for="name">Phường</label>
                                                    <form:input path="ward" class="form-control"></form:input>
                                                </div>

                                                <div class="col-sm-5">
                                                    <label for="name">Đường</label>
                                                    <form:input path="street" class="form-control"></form:input>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <label for="name">Số tầng hầm</label>
                                                    <form:input type="number" path="numberOfBasement"
                                                                class="form-control"></form:input>
                                                </div>

                                                <div class="col-sm-4">
                                                    <label for="name">Hướng</label>
                                                    <form:input path="direction" class="form-control"></form:input>
                                                </div>

                                                <div class="col-sm-4">
                                                    <label for="name">Hạng</label>
                                                    <form:input path="level" class="form-control"></form:input>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-3">
                                                    <label for="name">Diện tích từ</label>
                                                    <form:input path="areaFrom" class="form-control"></form:input>
                                                </div>

                                                <div class="col-sm-3">
                                                    <label for="name">Diện tích đến</label>
                                                    <form:input path="areaTo" class="form-control"></form:input>
                                                </div>

                                                <div class="col-sm-3">
                                                    <label for="name">Giá thuê từ</label>
                                                    <form:input path="rentPriceFrom" class="form-control"></form:input>
                                                </div>

                                                <div class="col-sm-3">
                                                    <label for="name">Giá thuê đến</label>
                                                    <form:input path="rentPriceTo" class="form-control"></form:input>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-5">
                                                    <label for="name">Tên quản lý</label>
                                                    <form:input type="number" path="managerName"
                                                                class="form-control"></form:input>
                                                </div>

                                                <div class="col-sm-5">
                                                    <label for="name">SĐT quản lý</label>
                                                    <form:input path="managerPhone" class="form-control"></form:input>
                                                </div>

                                                <div class="col-sm-2">
                                                    <label for="name">Nhân viên phụ trách</label>
                                                    <form:select path="staffId">
                                                        <form:option value=""
                                                                     label="---Chọn nhân viên---"></form:option>
                                                        <form:options items="${staffs}"></form:options>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button class="btn btn-sm btn-primary" id="btnSearch">
                                                        <i class="ace-icon glyphicon glyphicon-search"></i>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <div class="pull-right">
                        <a href="/admin/building-edit">
                            <button title="Thêm tòa nhà" class="btn btn-primary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </a>

                        <button title="Xóa tòa nhà" class="btn btn-danger" id="btnDeleteBuildings">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-building-dash" viewBox="0 0 16 16">
                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                            </svg>
                        </button>
                    </div>
                </div>
            </div><!-- /.row -->

            <div class="hr hr-18 dotted hr-double"></div>

            <div class="row">
                <div class="col-xs-12">
                    <table id="buildingList" class="table table-striped table-bordered table-hover"
                           style="font-family: 'Times New Roman', Times, serif;">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace"/>
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên tòa nhà</th>
                            <th>Địa chỉ</th>
                            <th>Số tầng hầm</th>
                            <th>Tên quản lý</th>
                            <th>SĐT quản lý</th>
                            <th>DT sàn</th>
                            <th>DT trống</th>
                            <th>DT thuê</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Phí môi giới</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="item" items="${buildings}">
                        <tr>
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" value="${item.id} "/>
                                    <span class="lbl"></span>
                                </label>
                            </td>
                            <td>${item.name}</td>
                            <td>${item.address}</td>
                            <td>${item.numberOfBasement}</td>
                            <td>${item.managerName}</td>
                            <td>${item.managerPhone}</td>
                            <td>${item.floorArea}</td>
                            <td>${item.emptyArea}</td>
                            <td>${item.rentArea}</td>
                            <td>${item.rentPrice}</td>
                            <td>${item.serviceFee}</td>
                            <td>${item.brokerageFee}</td>
                            <td>
                                <div>
                                    <button class="btn btn-sm btn-success" title="Giao tòa nhà"
                                            onclick="assignmentBuilding(${item.id})">
                                        <i class="ace-icon glyphicon glyphicon-align-justify"></i>
                                    </button>

                                    <a class="btn btn-sm btn-info" title="Sửa tòa nhà"
                                       href="/admin/building-edit-${item.id}">
                                        <i class="ace-icon fa fa-pencil-square-o"></i>
                                    </a>

                                    <button class="btn btn-sm btn-danger" title="Xóa tòa nhà"
                                            onclick="onclick=btnDeleteBuilding(${item.id})">
                                        <i class="ace-icon glyphicon glyphicon-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style="color:black;">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover" id="staffList">
                    <thead>
                    <tr>
                        <th class="center">
                            Chọn
                        </th>
                        <th class="center">Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="center">
                            <input type="checkbox" class="center" id="checkbox_1" value="1" checked>
                        </td>
                        <td class="text-center">Bùi Thị Dung</td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal()
        $('#buildingId').val(buildingId)
        loadStaffs(buildingId)
    }

    function loadStaffs(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + "/staffs",
            dataType: "json",
            success: (respone) => {
                var row = ''
                $.each(respone.data, function (index, item) {
                    row += '<tr>'
                    row += '<td class="center"><input type="checkbox" value=\"' + item.staffId + '\"  id="checkbox_' + item.staffId + '\"  ' + item.checked + '/></td>';
                    row += '<td class="text_center">' + item.fullName + '</td>'
                    row += '</tr>'
                })
                $('#staffList tbody').html(row)
            },
            error: function (respone) {
                console.log("failed")
                console.log(respone)
            }
        })
    }

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault()
        var data = {}
        data['buildingId'] = $('buildingId').val()
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val()
        }).get()
        data['staffs'] = staffs

        $.ajax({
            type: "PUT",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: (respone) => {
                alert("Giao thanh cong")
            },
            error: (respone) => {
                alert("Failed")
            }
        })
    })

    $('btnDeleteBuildings'.click(function (e) {
        e.preventDefault()
        var data = {}
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val()
        }).get()
        data['ids'] = buildingIds

        deleteBuilding(data)
    }))

    function btnDeleteBuilding(buildingId) {
        var data = {}
        var id = [buildingId]
        data['ids'] = id
        deleteBuilding(data)
    }

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}",
            data: JSON.stringify(data['ids']),
            contentType: "application/json",
            dataType: "text",
            success: (respone) => {
                alert(respone)
                window.location.replace("/admin/building-list")
            },
            error: function (respone) {
                console.log("Xóa không thành công")
                console.log(respone)
            }
        })
    }


    $('$btnSearch').click(function (e) {
        e.preventDefault()
        $('listForm').submit()
    })
</script>

</body>
</html>
