<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/admin/_auth.jspf" %>

<%@ page import="java.util.Date" %>

<%

%>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<meta name="author" content="4what" />

<link rel="shortcut icon" href="http://www.4what.cn/favicon.ico" />

<%@ include file="_script_style.jspf" %>

<script type="text/javascript">
Ext.onReady(function() {

	// vtypes
	Ext.apply(Ext.form.VTypes, $ext.vtypes.password2());


	// nav
	var _nav = new Ext.Panel({
		region: "west",
		title: "",
		collapseMode: "mini",
		collapsible: true,
		iconCls: "icon-accordion",
		layout: {
			type: "accordion",
			animate: true
		},
		split: true,
		width: 200,
		defaults: {
			xtype: "treepanel",
			autoScroll: true,
			border: false,
			containerScroll: true,
			rootVisible: false,
			useArrows: true,
			listeners: {
				click: function(node, e) {
					$ext.tab.loadOnClickLeaf(node, e, _main, {
						iconCls: "icon-grid"
					});
				}
			}
		},
		items: [
			// 自定义
			{
				title: "控制面板",
				iconCls: "icon-ctrl",
				root: {
					expanded: true,
					children: [
						{
							text: "活动管理",
							expanded: true,
							singleClickExpand: true,
							children: [
								{
									text: "抽奖管理",
									leaf: true,
									href: "winner.jsp"
								}
							]
						},
						{
							text: "系统配置",
							iconCls: "icon-folder-setting",
							singleClickExpand: true,
							children: [
								{
									text: "来宾帐户",
									leaf: true,
									href: "guest.jsp"
								},
								{
									text: "设置管理",
									leaf: true,
									href: "setting.jsp"
								},
								{
									text: "操作日志",
									leaf: true,
									href: "log.jsp"
								},
								{
									text: "系统日志",
									leaf: true,
									href: "debug.jsp"
								}
							]
						},
						{
							text: "用户管理",
							iconCls: "icon-group",
							singleClickExpand: true,
							children: [
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								{
									text: "帐户管理",
									leaf: true,
									href: "admin.jsp"
								},
								{
									text: "角色管理",
									leaf: true,
									href: "role.jsp"
								},
								{
									text: "权限管理",
									leaf: true,
									href: "acl.jsp"
								},
								</sec:authorize>
								{
									text: "修改密码",
									leaf: true,
									listeners: {
										click: function(node, e) {
											_password.win.show();

											return false;
										}
									}
								}
							]
						}
					]
				}
			},
			{
				title: "帮助文档",
				iconCls: "icon-doc",
				root: {}
			}
		],
		tbar: {
			items: [
				{
					text:
						<%--"<%= _admin.getUsername() %>"--%>
						"<sec:authentication property="name" />"
					,
					xtype: "tbtext",
					cls: "icon-user icon-text"
				},
				"->",
				{
					text: "退出",
					xtype: "button",
					iconCls: "icon-logout",
					handler: function(button, e) {
						// 自定义
						window.location.href =
							//"logout.jsp"
							"logout"
						;
					}
				}
			]
		},
		bbar: {
			buttonAlign: "right",
			items: [
				{
					xtype: "button",
					iconCls: "icon-expand",
					handler: function(button, e) {
						$ext.tree.toggle(_nav, true);
					}
				},
				"-",
				{
					xtype: "button",
					iconCls: "icon-collapse",
					handler: function(button, e) {
						$ext.tree.toggle(_nav, false);
					}
				}
			]
		}
	});


	// main
	var _main = new Ext.TabPanel({
		region: "center",
		id: "main",
		activeTab: 0,
		border: false,
		enableTabScroll: true,
		plugins: new Ext.ux.TabCloseMenu(),
		items: [
			{
				title: "Welcome!",
				//closable: true,
				contentEl: "welcome",
				frame: true,
				iconCls: "icon-tab"
			}
		]
	});


	// password
	var _password = (function () {
		var that = {};

		that.form = new Ext.form.FormPanel({
			//autoScroll: true,
			buttonAlign: "center",
			frame: true,
			//height: 600, // 自定义
			labelAlign: "right",
			labelWidth: 100, // 自定义
			padding: "15px 15px 0 15px",
			waitMsgTarget: true,
			defaults: {
				//msgTarget: "under",
				width: 210 // 自定义
			},
			items: [
				// 自定义
				{
					fieldLabel: "ID",
					xtype: "hidden",
					//xtype: "textfield",
					name: "adminId",
					value: "<%= _admin.getAdminId() %>"
				},
				{
					fieldLabel: "用户名",
					xtype: "displayfield",
					value: "<%= _admin.getUsername() %>"
				},
				{
					fieldLabel: "当前密码",
					xtype: "textfield",
					inputType: "password",
					name: "oldPassword",
					allowBlank: false
				},
				{
					id: "password",
					fieldLabel: "新密码",
					xtype: "textfield",
					inputType: "password",
					name: "password",
					allowBlank: false,
					maxLength: 16,
					minLength: 6
				},
				{
					fieldLabel: "确认密码",
					xtype: "textfield",
					name: "password2",
					inputType: "password",
					allowBlank: false,
					maxLength: 16,
					minLength: 6,
					vtype: "password2",
					PasswordField: "password"
				}
			],
			buttons: [
				{
					text: "提交",
					xtype: "button",
					iconCls: "icon-submit",
					handler: function(button, e) {
						that.submit();
					}
				},
				{
					text: "重置",
					xtype: "button",
					handler: function(button, e) {
						that.reset();
					}
				}
			],
			keys: {
				key: Ext.EventObject.ENTER,
				ctrl: true,
				fn: function(key, e) {
					that.submit();
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

		that.reset = function() {
			that.form.getForm().reset();
		};

		that.submit = function() {
			if (that.form.getForm().isValid()) {
				that.form.getForm().submit({
					url:
						//"action/do_admin.jsp"
						"account/changePassword"
					, // 自定义
					method: "POST",
					params: {
						// 自定义
						action: "changePassword"
					},
					success: function(form, action) {
						Ext.Msg.alert("", "操作成功", function(buttonId, text, opt) {
							// 自定义
							that.win.hide();
						});
					},
					failure: function(form, action) {
						var
						msg,
						result = action.result;
						if (result) {
							switch (result.msg) {
								// 自定义
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
				});
			}
		};

		that.win = new Ext.Window({
			title: "修改密码",
			autoScroll: true,
			border: false,
			closeAction: "hide",
			constrainHeader: true,
			//height: 600, // 自定义
			iconCls: "icon-user-edit",
			maximizable: true,
			modal: true,
			plain: true,
			width: 480, // 自定义
			items: [that.form],
			listeners: {
				beforeshow: function(cmp) {
					that.reset();
				}
			}
		});

		return that;
	})();


	// viewport
	var _viewport = new Ext.Viewport({
		layout: "border",
		items: [
			_nav,
			_main
		]
	});


/*
	// homepage
	// 自定义
	$ext.tab.load(_main, {
		title: "主页",
		src: ".jsp",
		iconCls: "icon-grid"
	});
*/

});
</script>
</head>

<body>

<div style="display: none;">

<div id="welcome">
	<p>
		<fmt:formatDate value="<%= new Date() %>" pattern="yyyy-MM-dd HH:mm:ss" />
	</p>
</div>

</div>

</body>
</html>
