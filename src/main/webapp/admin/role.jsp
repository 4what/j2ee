<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_ADMIN')">

<%@ include file="/admin/_auth.jspf" %>

<%

%>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>

	<%@ include file="_script_style.jspf" %>

	<script type="text/javascript">
	Ext.onReady(function() {

		/* pagesize */
		var _pagesize = 20; // (自定义)


		/* jsonstore */
		var _jsonstore = new
			Ext.data.GroupingStore
			({
				url:
					//"action/do_role.jsp"
					"role/list"
				, // (自定义)
				autoDestroy: true,
				autoLoad: {
					params: {
						start: 0
					}
				},
				baseParams: {
					//limit: _pagesize,
					// (自定义)
					action: "list"
				},
				reader: new Ext.data.JsonReader({
					fields: [
						// (自定义)
						{name: "id"},

						{name: "group"},
						{name: "roles"}

	/*
						{name: "status"},

						{name: "createDate", type: "date"}
	*/
					],
					root: "rows",
					totalProperty: "total"
				}),
	/*
				sortInfo: {
					// (自定义)
					direction: "DESC",
					field: "createDate"
				},
	*/

				/* for GroupingStore */
				groupField: "", // (自定义)

				listeners: {
					beforeload: function(store, options) {
	/*
						$ext.store.setBaseParam(
							store,
							"[id^='search-']" // (自定义)
						);
	*/
					}
				}
			});


		/* sm */
		var _sm = new
			Ext.grid.CheckboxSelectionModel
			({
				listeners: {
					selectionchange: function(sm) {
						var
						count = sm.getCount(),
						records = sm.getSelections();

						//_grid["btn-del"].setDisabled(count < 1);
						_grid["btn-update"].setDisabled(count !== 1);
						//_grid["btn-save"].setDisabled(count < 1);

						// (自定义)
					}
				}
			});


		/* grid */
		var _grid = new
			Ext.grid.EditorGridPanel
			({
				region: "center",
				clicksToEdit: 1,
				columnLines: true,
				//ddGroup: "GridToTree",
				//enableDragDrop: true,
				loadMask: true,
				plugins: [],
				stripeRows: true,
				sm: _sm,
				store: _jsonstore,
				view: new
					Ext.grid.GroupingView
					({
						//markDirty: false,

						/* for GroupingView */
						groupTextTpl: '{text} ({[values.rs.length]})'
					}),
				cm: new
					Ext.grid.ColumnModel
					({
						defaults: {
							sortable: true
						},
						columns: [
							_sm,

							// (自定义)
	/*
							{
								header: "ID",
								dataIndex: "id",
								width: 80
							},
	*/
							{
								header: "组",
								dataIndex: "group",
								width: 160
							},
							{
								header: "角色",
								dataIndex: "roles",
								width: 240
							}
	/*
							{
								header: "状态",
								dataIndex: "status",
								renderer: function(value, metaData, record, rowIndex, colIndex, store) {
									switch (value) {
										case 0:
											return "正常";
										default:
											return value;
									}
								},
								width: 60
							},
							{
								header: "创建时间",
								dataIndex: "createDate",
								xtype: "datecolumn",
								format: "Y-m-d H:i:s",
								width: 130
							}
	*/
						]
					}),
				tbar: [
					{
						xtype: "buttongroup",
						items: [
							{
								text: "新增",
								xtype: "button",
								iconCls: "icon-add",
								handler: function(button, e) {
									_win.show().setTitle("新增");
								}
							},
							{
								ref: "../../btn-del",
								text: "删除",
								xtype: "button",
								disabled: true,
								iconCls: "icon-del",
								handler: function(button, e) {
									Ext.Msg.confirm("", "确定删除？", function(buttonId, text, opt) {
										switch (buttonId) {
											case "yes":
												var
												ids = [],
												records = _grid.getSelectionModel().getSelections();
												Ext.each(records, function(item, index, allItems) {
													ids.push(item.get(
														"id" // (自定义)
													));
												});
												_grid.body.mask("请稍候……");
												Ext.Ajax.request({
													url:
														//"action/do_role.jsp"
														"role/delete"
													, // (自定义)
													method: "POST",
													params: {
														// (自定义)
														action: "delete",
														id: ids
													},
													callback: function(options, success, response) {
														_grid.body.unmask();
													},
													success: function(response, options) {
														var
														msg,
														result = Ext.util.JSON.decode(response.responseText);
														if (result.success) {
															_jsonstore.remove(records);
														} else {
															switch (result.msg) {
																// (自定义)
																case "":
																	msg = "";
																	break;
																default:
																	msg = result.msg;
																	break;
															}
															Ext.Msg.alert("错误", msg).setIcon(Ext.Msg.ERROR);
														}
													},
													failure: $ext.failure.ajax
												});
												break;
											case "no":
												break;
											default:
												break;
										}
									});
								}
							},
							{
								ref: "../../btn-update",
								text: "修改",
								xtype: "button",
								disabled: true,
								iconCls: "icon-edit",
								listeners: {
									click: function(button, e) {
										var
										record = _grid.getSelectionModel().getSelected(),
										id = record.get(
											"id" // (自定义)
										);

										_win.show().setTitle("修改");

										_form.getForm().loadRecord(record);

										// (自定义)
										var roles = Ext.getCmp("form-multiselect-role");
										roles.store.loadData(record.get("roles"));
										roles.clearInvalid();
									}
								}
							}
	/*
							{
								ref: "../../btn-save",
								text: "保存",
								xtype: "button",
								disabled: true,
								iconCls: "icon-save",
								handler: function(button, e) {
									var data = [];
									Ext.each(_grid.getSelectionModel().getSelections(), function(item, index, allItems) {
										if (item.dirty) {
											data.push(item.data);
										}
									});
									if (data.length === 0) {
										return;
									}
									_grid.body.mask("请稍候……");
									Ext.Ajax.request({
										url:
											//"action/do_role.jsp"
											"role/save"
										, // (自定义)
										method: "POST",
										params: {
											// (自定义)
											action: "save",
											data: Ext.util.JSON.encode(data)
										},
										callback: function(options, success, response) {
											_grid.body.unmask();
										},
										success: function(response, options) {
											var
											msg,
											result = Ext.util.JSON.decode(response.responseText);
											if (result.success) {
												Ext.Msg.alert("", "操作成功", function(buttonId, text, opt) {
													// (自定义)
													_jsonstore.reload();
												});
											} else {
												switch (result.msg) {
													// (自定义)
													case "":
														msg = "";
														break;
													default:
														msg = result.msg;
														break;
												}
												Ext.Msg.alert("错误", msg).setIcon(Ext.Msg.ERROR);
											}
										},
										failure: $ext.failure.ajax
									});
								}
							}
	*/
						]
					},
					"-",
					{
						text: "刷新",
						xtype: "button",
						iconCls: "icon-refresh",
						handler: function(button, e) {
							_jsonstore.reload();
						}
					},
					"->"
				],
	/*
				bbar: {
					xtype: "paging",
					displayInfo: true,
					pageSize: _pagesize,
					plugins: new Ext.ux.ProgressBarPager(),
					prependButtons: true,
					store: _jsonstore
				},
	*/
				listeners: {
					rowdblclick: function(grid, rowIndex, e) {
						// (自定义)
						_grid["btn-update"].fireEvent("click");
					}
				}
			});


		/* form */
		var _form = new Ext.form.FormPanel({
			//autoScroll: true,
			buttonAlign: "center",
			frame: true,
			//height: 600, // (自定义)
			labelAlign: "right",
			labelWidth: 100, // (自定义)
			padding: "15px 15px 0 15px",
			waitMsgTarget: true,
			defaults: {
				//msgTarget: "under",
				width: 210 // (自定义)
			},
			items: [
				// (自定义)
				{
					fieldLabel: "ID",
					xtype: "hidden",
					//xtype: "textfield",
					name: "id"
				},
				{
					fieldLabel: '<span style="color: red;">*</span> 组',
					xtype: "textfield",
					name: "group",
					allowBlank: false
				},
				{
					fieldLabel: '<span style="color: red;">*</span> 角色',
					xtype: "compositefield",
					autoWidth: true,
					items: [
						{
							id: "form-combo-role",
							xtype: "combo",
							//name: "",
							//hiddenName: "",
							//editable: false,
							//forceSelection: true,
							lazyRender: true,
							listWidth: 260,
							//queryParam: "", // query
							resizable: true,
							triggerAction: "all", // query|all
							width: 210,

							/* autocomplete */
							//minChars: 2,

							store: ["<%= Role.ADMIN %>", "<%= Role.USER %>", "<%= Role.GUEST %>"]
							//pageSize: 10,
							//displayField: "text",
							//valueField: "value",

							//emptyText: "请选择"
						},
						{
							//text: "新增",
							xtype: "button",
							autoWidth: true,
							iconCls: "icon-add",
							handler: function(button, e) {
								var
								combo = Ext.getCmp(
									"form-combo-role" // (自定义)
								),
								//text = combo.getRawValue(),
								value = combo.getValue();
								if (value) {
									$ext.multiselect.add(
										"form-multiselect-role", // (自定义)
										value
									);
									combo.reset();
								}
							}
						}
					]
				},
				{
					id: "form-multiselect-role",
					fieldLabel: "",
					xtype: "multiselect",
					name: "roles",
					allowBlank: false,
					//ddReorder: true,
					height: 160,
					//maxSelections: 3,
					//minSelections: 2,
					width: 210,
					style: {
						"overflow": "hidden"
					},

					store: [],
					//displayField: "text",
					//valueField: "value",

					tbar: [
						{
							//text: "删除",
							xtype: "button",
							iconCls: "icon-del",
							handler: function(button, e) {
								$ext.multiselect.del(
									"form-multiselect-role" // (自定义)
								);
							}
						}
					]
				}
			],
			buttons: [
				{
					text: "提交",
					xtype: "button",
					iconCls: "icon-submit",
					handler: function(button, e) {
						_submit();
					}
				},
				{
					text: "重置",
					xtype: "button",
					handler: function(button, e) {
						_reset();
					}
				}
			],
			keys: {
				key: Ext.EventObject.ENTER,
				ctrl: true,
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


		/* reset */
		function _reset() {
			$ext.multiselect.clear(
				"[id^='form-multiselect-']" // (自定义)
			);

			_form.getForm().reset();
		}


		/* submit */
		function _submit() {
			$ext.multiselect.selectAll(
				"[id^='form-multiselect-']" // (自定义)
			);

			if (_form.getForm().isValid()) {
				_form.getForm().submit({
					url:
						//"action/do_role.jsp"
						"role/save"
					, // (自定义)
					method: "POST",
					params: {
						// (自定义)
						action: "save"
					},
					success: function(form, action) {
						Ext.Msg.alert("", "操作成功", function(buttonId, text, opt) {
							// (自定义)
							_win.hide();
							_jsonstore.reload();
						});
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
				});
			}
		}


		/* window */
		var _win = new Ext.Window({
			autoScroll: true,
			border: false,
			closeAction: "hide",
			constrainHeader: true,
			//height: 600, // (自定义)
			iconCls: "icon-form",
			maximizable: true,
			modal: true,
			plain: true,
			width: 480, // (自定义)
			items: [_form],
			listeners: {
				beforeshow: function(cmp) {
					_reset();
				}
			}
		});


		/* search */
	/*
		var _search = new Ext.Panel({
			region: "north",
			title: "",
			frame: true,
			height: 50, // 50|80|110 // (自定义)
			layout: {
				type: "vbox",
				align: "stretch",
				defaultMargins: "5px 0",
				pack: "center"
			},
			split: true,
			defaults: {
				layout: {
					type: "hbox",
					defaultMargins: "0 5px"
				}
			},
			items: [
				// (自定义)
				{
					items: [
						// (自定义)
						{
							id: "search-status",
							xtype: "combo",
							name: "status",
							editable: false,
							lazyRender: true,
							resizable: true,
							triggerAction: "all",
							width: 80,
							store: [["", "全部"], ["0", "正常"]],
							emptyText: "状态"
						},
						new Ext.ux.form.SearchField({
							width: 360,
							store: _jsonstore,
							emptyText: "关键字"
						})
					]
				}
			]
		});
	*/


		/* viewport */
		var _viewport = new Ext.Viewport({
			//autoScroll: true,
			layout: "border",
			items: [
				// (自定义)
				{
					region: "center",
					border: false,
					layout: "border",
					items: [
						// (自定义)
						//_search,
						_grid
					]
				}
			]
		});

	});
	</script>
</head>
<body>
	<div style="display: none;"></div>
</body>
</html>

</sec:authorize>
