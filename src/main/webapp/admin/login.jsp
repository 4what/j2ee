<%@ page contentType="text/html; charset=UTF-8" %>

<%

%>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>

	<%@ include file="_script_style.jspf" %>

	<script type="text/javascript">
	/* for timeout */
	if (window != window.top) {
		window.top.location.href = "login.jsp";
	}


	Ext.onReady(function() {

		/* form */
		var _form = new Ext.form.FormPanel({
			standardSubmit: true,
			url: "login",
			method: "POST",

			baseCls: "x-plain",
			buttonAlign: "center",
			frame: true,
			labelAlign: "right",
			labelWidth: 75,
			padding: "15px 0 0 15px",
			waitMsgTarget: true,
			width: 350,
			items: [
				{
					title: "帐户",
					xtype: "fieldset",
					defaults: {
						width: 210
					},
					items: [
						{
							fieldLabel: "用户名",
							xtype: "textfield",
							name: "username", // (自定义)
							allowBlank: false
						},
						{
							fieldLabel: "密　码",
							xtype: "textfield",
							inputType: "password",
							name: "password", // (自定义)
							allowBlank: false
						},
						{
							xtype: "checkbox",
							name: "remember-me",
							boxLabel: "记住我",
							checked: true,
							inputValue: "1"
						}
					]
				},
				// (自定义)
				{
					xtype: "hidden",
					name: "return",
					value: ""
				}
			],
			buttons: [
				{
					text: "登录",
					xtype: "button",
					handler: function(button, e) {
						_submit();
					}
				},
				{
					text: "重置",
					xtype: "button",
					handler: function(button, e) {
						_form.getForm().reset();
					}
				}
			],
			keys: {
				key: Ext.EventObject.ENTER,
				fn: function(key, e) {
					_submit();
				}
			},
			listeners: {
				beforeaction: function(form, action) {
					this.getFooterToolbar().disable();
				},
				actioncomplete: function(form, action) {
					this.getFooterToolbar().enable();
				},
				actionfailed: function(form, action) {
					this.getFooterToolbar().enable();
				}
			}
		});


		/* submit */
		function _submit() {
			if (_form.getForm().isValid()) {
				_form.getForm().el.dom.action = _form.url; // TODO: bug? Std

				_form.getForm().submit({
/*
					url: "action/do_login.jsp", // (自定义)
					method: "POST",
					params: {
						// (自定义)
						action: "login"
					},
					success: function(form, action) {
						// (自定义)
						window.location.href = "index.jsp";
					},
					failure: function(form, action) {
						var
						msg,
						result = action.result;
						if (result) {
							switch (result.msg) {
								// (自定义)
								case "":
									msg = "";
									break;
								default:
									msg = result.msg;
									break;
							}
						}
						$ext.failure.form(form, action, msg);
					},
					waitMsg: "请稍候……"
*/
				});
			}
		}


		/* window */
		new Ext.Window({
			title: "管理员登录",
			border: false,
			buttonAlign: "center",
			closable: false,
			constrain: true,
			iconCls: "icon-login",
			modal: true,
			plain: true,
			resizable: false,
			width: 380,
			items: [_form]
			//buttons: ["&copy; "]
		}).show();


		/* error */
		if ("${param.error}") {
			Ext.Msg.alert("错误", "${SPRING_SECURITY_LAST_EXCEPTION.message}").setIcon(Ext.Msg.ERROR);
		}

	});
	</script>
</head>
<body>
	<div style="display: none;"></div>
</body>
</html>
