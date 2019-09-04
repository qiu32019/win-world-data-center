<!DOCTYPE html>
<html>
<head>
    <#import "./common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <!-- DataTables -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <title>${I18n.admin_name}</title>
</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && cookieMap["xxljob_adminlte_settings"]?exists && "off" == cookieMap["xxljob_adminlte_settings"].value >sidebar-collapse</#if> ">
<div class="wrapper">
    <!-- header -->
    <@netCommon.commonHeader />
    <!-- left -->
    <@netCommon.commonLeft "jobgroup" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>${I18n.jobgroup_name}</h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">网站信息列表</h3>&nbsp;&nbsp;
                            <button class="btn btn-info btn-xs pull-left2 add" >新增网站</button>
                        </div>
                        <div class="box-body">
                            <table id="joblog_list" class="table table-bordered table-striped display" width="100%" >
                                <thead>
                                <tr>
                                    <#--<th name="id" >ID</th>-->
                                    <th name="order" >体育编号</th>
                                    <th name="appName" >前缀</th>
                                    <th name="title" >IP地址</th>
                                    <th name="addressType" >真人项目</th>
                                    <th name="registryList" >PT点数</th>
                                    <th name="operate" >操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if list?exists && list?size gt 0>
                                    <#list list as group>
                                        <tr>
                                            <#--<td>${group.id}</td>-->
                                            <td>${group.siteNo}</td>
                                            <td>${group.prefix}</td>
                                            <td>${group.ip}</td>
                                            <td>${group.allowedtype}</td>
                                            <td>
                                                ${group.ptscore}
                                            </td>
                                            <td>
                                                <button class="btn btn-warning btn-xs update"
                                                        sportSiteNo="${group.sportSiteNo}"
                                                        uppername="${group.uppername}"
                                                        prefix="${group.prefix}"
                                                        enable="${group.enable}"
                                                        ip="${group.ip}"
                                                        ptscore="${group.ptscore}"
                                                        perbatch="${group.perbatch}"
                                                        allowedtype="${group.allowedtype}">修改</button>
                                                <button class="btn btn-danger btn-xs remove" id="${group.siteNo}" >${I18n.system_opt_del}</button>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- 新增.模态框 -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog ">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" >${I18n.jobgroup_add}</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal form" role="form" >
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">体育SiteNo<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="text" class="form-control" name="sportSiteNo" placeholder="体育SiteNo" maxlength="64" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">BBIN上级账号<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="text" class="form-control" name="uppername" placeholder="BBIN上级账号" maxlength="12" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">前缀<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="text" class="form-control" name="prefix" placeholder="前缀" maxlength="50" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">开启状态<font color="red">*</font></label>
                            <div class="col-sm-10">
                                <input type="radio" name="enable" value="0" checked />启用
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="enable" value="1" />停用
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">ip地址<font color="red">*</font></label>
                            <div class="col-sm-10">
                                <textarea class="textarea" name="ip" maxlength="512" placeholder="ip地址用竖线分隔,如:|192.168.2.25|192.168.2.26|192.168.2.27|" <#--readonly="readonly"--> style="background-color:#eee; width: 100%; height: 100px; font-size: 14px; line-height: 15px; border: 1px solid #dddddd; padding: 5px;"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">PT点数<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="number" class="form-control" name="ptscore" placeholder="PT点数" maxlength="50" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">接水单批数量<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="number" class="form-control" name="perbatch" placeholder="接水单批数量" maxlength="50" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">真人开通种类<font color="red">*</font></label>
                            <div class="col-sm-10">
                                <#--<textarea class="textarea" name="allowedtype" maxlength="512" placeholder="真人开通种类" readonly="readonly" style="background-color:#eee; width: 100%; height: 100px; font-size: 14px; line-height: 15px; border: 1px solid #dddddd; padding: 5px;"></textarea>-->
                                <#if ZRSites?exists && ZRSites?size gt 0>
                                    <#list ZRSites as item>
                                        <input type="checkbox" name="allowedtype" value="${item.desc}" />${item.desc}
                                    </#list>
                                </#if>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-6">
                                <button type="submit" class="btn btn-primary"  >${I18n.system_save}</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">${I18n.system_cancel}</button>
                                <input type="hidden" name="id" >
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 更新.模态框 -->
        <div class="modal fade" id="updateModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog ">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" >${I18n.jobgroup_edit}</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal form" role="form" >
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">体育SiteNo<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="text" class="form-control" name="sportSiteNo" placeholder="体育SiteNo" maxlength="64" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">BBIN上级账号<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="text" class="form-control" name="uppername" placeholder="BBIN上级账号" maxlength="12" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">前缀<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="text" class="form-control" name="prefix" placeholder="前缀" maxlength="50" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">开启状态<font color="red">*</font></label>
                            <div class="col-sm-10">
                                <input type="radio" name="enable" value="0" />启用
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="enable" value="1" />停用
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">ip地址<font color="red">*</font></label>
                            <div class="col-sm-10">
                                <textarea class="textarea" name="ip" maxlength="512" placeholder="ip地址用竖线分隔,如:|192.168.2.25|192.168.2.26|192.168.2.27|" <#--readonly="readonly"--> style="background-color:#eee; width: 100%; height: 100px; font-size: 14px; line-height: 15px; border: 1px solid #dddddd; padding: 5px;"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">PT点数<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="number" class="form-control" name="ptscore" readonly="readonly" placeholder="PT点数" maxlength="50" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">接水单批数量<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="number" class="form-control" name="perbatch" placeholder="接水单批数量" maxlength="50" ></div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">真人开通种类<font color="red">*</font></label>
                            <div class="col-sm-10">
                                <#--<textarea class="textarea" name="allowedtype" maxlength="512" placeholder="真人开通种类" readonly="readonly" style="background-color:#eee; width: 100%; height: 100px; font-size: 14px; line-height: 15px; border: 1px solid #dddddd; padding: 5px;"></textarea>-->
                                <#if ZRSites?exists && ZRSites?size gt 0>
                                    <#list ZRSites as item>
                                        <input type="checkbox" name="allowedtype" value="${item.desc}" />${item.desc}
                                    </#list>
                                </#if>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-6">
                                <button type="submit" class="btn btn-primary"  >${I18n.system_save}</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">${I18n.system_cancel}</button>
                                <input type="hidden" name="id" >
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- footer -->
    <@netCommon.commonFooter />
</div>

<@netCommon.commonScript />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/js/jobgroup.index.1.js"></script>
</body>
</html>
