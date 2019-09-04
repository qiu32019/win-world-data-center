$(function() {

	// remove
	$('.remove').on('click', function(){
		var id = $(this).attr('id');

		layer.confirm( (I18n.system_ok + I18n.jobgroup_del + '？') , {
			icon: 3,
			title: I18n.system_tips ,
            btn: [ I18n.system_ok, I18n.system_cancel ]
		}, function(index){
			layer.close(index);

			$.ajax({
				type : 'POST',
				url : base_url + '/jobgroup/remove',
				data : {"id":id},
				dataType : "json",
				success : function(data){
					if (data.code == 200) {
						layer.open({
							title: I18n.system_tips ,
                            btn: [ I18n.system_ok ],
							content: (I18n.jobgroup_del + I18n.system_success),
							icon: '1',
							end: function(layero, index){
								window.location.reload();
							}
						});
					} else {
						layer.open({
							title: I18n.system_tips,
                            btn: [ I18n.system_ok ],
							content: (data.msg || (I18n.jobgroup_del + I18n.system_fail)),
							icon: '2'
						});
					}
				},
			});
		});

	});

	$('.add').on('click', function(){
		$('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	var addModalValidate = $("#addModal .form").validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : true,
		rules : {

		},
		messages : {

		},
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');
		},
		success : function(label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},
		errorPlacement : function(error, element) {
			element.parent('div').append(error);
		},
		submitHandler : function(form) {
			var allowedtypeArr = [];
			$("#addModal .form input[name=allowedtype]:checked").each(function(){
				allowedtypeArr.push($(this).val());
			})

			var paramData = {
				"siteNo": $("#addModal .form input[name=siteNo]").val(),
				"sportSiteNo": $("#addModal .form input[name=sportSiteNo]").val(),
				"site203": $("#addModal .form input[name=site203]").val(),
				"ip": $("#addModal .form input[name=ip]").val(),
				"uppername": $("#addModal .form input[name=uppername]").val(),
				"deskey": $("#addModal .form input[name=deskey]").val(),
				"allowedtype": allowedtypeArr.join('|'),
				"perbatch": $("#addModal .form input[name=perbatch]").val(),
				"prefix": $("#addModal .form input[name=prefix]").val(),
				"enable": $("#addModal .form input[name=enable]:checked").val(),
				"ptscore": $("#addModal .form input[name=ptscore]").val()
			};

			$.post(base_url + "/jobgroup/save",  paramData, function(data, status) {
				if (data.code == "200") {
					$('#addModal').modal('hide');
					layer.open({
						title: I18n.system_tips ,
                        btn: [ I18n.system_ok ],
						content: I18n.system_add_suc ,
						icon: '1',
						end: function(layero, index){
							window.location.reload();
						}
					});
				} else {
					layer.open({
						title: I18n.system_tips,
                        btn: [ I18n.system_ok ],
						content: (data.msg || I18n.system_add_fail  ),
						icon: '2'
					});
				}
			});
		}
	});
	$("#addModal").on('hide.bs.modal', function () {
		$("#addModal .form")[0].reset();
		addModalValidate.resetForm();
		$("#addModal .form .form-group").removeClass("has-error");
	});

	// update
	$('.update').on('click', function(){
		$("#updateModal .form input[name='sportSiteNo']").val($(this).attr("sportSiteNo"));
		$("#updateModal .form input[name='uppername']").val($(this).attr("uppername"));
		$("#updateModal .form input[name='prefix']").val($(this).attr("prefix"));
		//$("#updateModal .form input[name='enable']").val($(this).attr("zenable"));

		$("#updateModal .form input[name='enable'][value='"+ $(this).attr("enable") +"']").click();

		$("#updateModal .form textarea[name='ip']").val($(this).attr("ip"));
		$("#updateModal .form input[name='ptscore']").val($(this).attr("ptscore"));
		$("#updateModal .form input[name='perbatch']").val($(this).attr("perbatch"));
		//$("#updateModal .form textarea[name='allowedtype']").val($(this).attr("allowedtype"));
		var allowedtypeArr = $(this).attr("allowedtype").split("|");
		$("#updateModal .form input[name='allowedtype']").each(function () {
			if($.inArray($(this).val(), allowedtypeArr) > -1) {
				$(this).prop("checked",true);
			} else {
				$(this).prop("checked",false);
			}
		});

		$('#updateModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	var updateModalValidate = $("#updateModal .form").validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : true,
		rules : {

		},
		messages : {

		},
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');
		},
		success : function(label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},
		errorPlacement : function(error, element) {
			element.parent('div').append(error);
		},
		submitHandler : function(form) {
			var allowedtypeArr = [];
			$("#updateModal .form input[name=allowedtype]:checked").each(function(){
				allowedtypeArr.push($(this).val());
			})

			var paramData = {
				"siteNo": $("#updateModal .form input[name=siteNo]").val(),
				"sportSiteNo": $("#updateModal .form input[name=sportSiteNo]").val(),
				"site203": $("#updateModal .form input[name=site203]").val(),
				"ip": $("#updateModal .form input[name=ip]").val(),
				"uppername": $("#updateModal .form input[name=uppername]").val(),
				"deskey": $("#updateModal .form input[name=deskey]").val(),
				"allowedtype": allowedtypeArr.join('|'),
				"perbatch": $("#updateModal .form input[name=perbatch]").val(),
				"prefix": $("#updateModal .form input[name=prefix]").val(),
				"enable": $("#updateModal .form input[name=enable]:checked").val(),
				"ptscore": $("#updateModal .form input[name=ptscore]").val()
			};

			$.post(base_url + "/jobgroup/update",  paramData, function(data, status) {
				if (data.code == "200") {
					$('#addModal').modal('hide');

					layer.open({
						title: I18n.system_tips ,
                        btn: [ I18n.system_ok ],
						content: I18n.system_update_suc ,
						icon: '1',
						end: function(layero, index){
							window.location.reload();
						}
					});
				} else {
					layer.open({
						title: I18n.system_tips,
                        btn: [ I18n.system_ok ],
						content: (data.msg || I18n.system_update_fail  ),
						icon: '2'
					});
				}
			});
		}
	});
	$("#updateModal").on('hide.bs.modal', function () {
		$("#updateModal .form")[0].reset();
		addModalValidate.resetForm();
		$("#updateModal .form .form-group").removeClass("has-error");
	});

	
});
