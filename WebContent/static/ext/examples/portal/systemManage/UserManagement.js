/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
// 用户管理
Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();

	var roleNameStore = Ext.create('Ext.data.JsonStore', {
		proxy : {
			type : 'ajax',
			url : appBaseUri + '/sys/sysuser/getRoleName',
			reader : {
				type : 'json',
				root : 'list',
				idProperty : 'ItemValue'
			}
		},
		fields : [ 'ItemText', 'ItemValue' ]
	});

	Ext.define('App.UserManagementWindow', {
		extend : 'Ext.window.Window',
		constructor : function(config) {
			config = config || {};
			Ext.apply(config, {
				title : '用户信息',
				width : 350,
				height : 280,
				bodyPadding : '10 5',
				modal : true,
				layout : 'fit',
				items : [ {
					xtype : 'form',
					fieldDefaults : {
						labelAlign : 'left',
						labelWidth : 90,
						anchor : '100%'
					},
					items : [ {
						name : "cmd",
						xtype : "hidden",
						value : 'new'
					}, {
						xtype : 'hiddenfield',
						name : 'id'
					}, {
						xtype : 'textfield',
						name : 'userName',
						fieldLabel : '用户名',
						allowBlank : false,
						maxLength : 30
					}, {
						xtype : 'textfield',
						name : 'password',
						fieldLabel : '密码',
						allowBlank : false,
						maxLength : 32
					}, {
						xtype : 'textfield',
						name : 'realName',
						fieldLabel : '姓名',
						maxLength : 30
					}, {
						xtype : 'textfield',
						name : 'tel',
						fieldLabel : '手机号',
						maxLength : 15
					}, {
						xtype : 'textfield',
						name : 'email',
						fieldLabel : '邮箱',
						vtype : 'email',
						maxLength : 30
					}, {
						xtype : 'textfield',
						name : 'lastLoginTime',
						fieldLabel : '最后登录时间',
						hidden : true
					}, {
						xtype : 'combobox',
						fieldLabel : '角色',
						name : 'roleName',
						store : roleNameStore,
						valueField : 'ItemValue',
						displayField : 'ItemText',
						typeAhead : true,
						queryMode : 'remote',
						emptyText : '请选择...',
						allowBlank : false,
						editable : false,
						listeners : {
							select : function(combo, record, index) {
								Ext.getCmp("usermanagementform-role").setValue(combo.getValue());
							}
						}
					}, {
						xtype : 'hiddenfield',
						id : 'usermanagementform-role',
						name : 'role'
					} ],
					buttons : [ '->', {
						id : 'usermanagementwindow-save',
						text : '保存',
						iconCls : 'icon-save',
						width : 80,
						handler : function(btn, eventObj) {
							var window = btn.up('window');
							var form = window.down('form').getForm();
							if (form.isValid()) {
								window.getEl().mask('数据保存中，请稍候...');
								var vals = form.getValues();
								Ext.Ajax.request({
									url : appBaseUri + '/sys/sysuser/saveSysUser',
									params : {
										cmd : vals['cmd'],
										id : vals['id'],
										userName : vals['userName'],
										password : vals['password'],
										realName : vals['realName'],
										tel : vals['tel'],
										email : vals['email'],
										role : vals['role']
									},
									method : "POST",
									success : function(response) {
										if (response.responseText != '') {
											var res = Ext.JSON.decode(response.responseText);
											if (res.success) {
												globalObject.msgTip('操作成功！');
												Ext.getCmp('usermanagementgrid').getStore().reload();
											} else {
												globalObject.errTip('用户名已存在，请输入唯一值！');
											}
										}
									},
									failure : function(response) {
										globalObject.errTip('操作失败！');
									}
								});
								window.getEl().unmask();
								window.close();
							}
						}
					}, {
						id : 'usermanagementwindow-cancel',
						text : '取消',
						iconCls : 'icon-cancel',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, {
						id : 'usermanagementwindow-accept',
						text : '确定',
						hidden : true,
						iconCls : 'icon-accept',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, '->' ]
				} ]
			});
			App.UserManagementWindow.superclass.constructor.call(this, config);
		}
	});

	Ext.define('Forestry.app.systemManage.UserManagement', {
		extend : 'Ext.ux.custom.GlobalGridPanel',
		region : 'center',
		initComponent : function() {
			var me = this;

			Ext.define('ModelList', {
				extend : 'Ext.data.Model',
				idProperty : 'id',
				fields : [ {
					name : 'id',
					type : 'long'
				}, 'userName', 'password', 'realName', 'tel', 'email', {
					name : 'lastLoginTime',
					type : 'datetime',
					dateFormat : 'Y-m-d H:i:s'
				}, {
					name : 'role',
					type : 'short'
				}, 'roleName' ]
			});

			var store = me.createStore({
				modelName : 'ModelList',
				proxyUrl : appBaseUri + '/sys/sysuser/getSysUser',
				proxyDeleteUrl : appBaseUri + '/sys/sysuser/deleteSysUser',
				extraParams : me.extraParams
			});

			var columns = [ {
				text : "ID",
				dataIndex : 'id',
				width : '5%'
			}, {
				text : "用户名",
				dataIndex : 'userName',
				width : '13%'
			}, {
				text : "姓名",
				dataIndex : 'realName',
				width : '10%'
			}, {
				text : "手机号",
				dataIndex : 'tel',
				width : '14%'
			}, {
				text : "邮箱",
				dataIndex : 'email',
				width : '15%'
			}, {
				text : "最后登录时间",
				dataIndex : 'lastLoginTime',
				width : '19%'
			}, {
				text : "角色",
				dataIndex : 'roleName',
				width : '12%',
				sortable : false
			}, {
				text : "roleId",
				dataIndex : 'role',
				hidden : true,
				sortable : false
			}, {
				xtype : 'actioncolumn',
				width : '8%',
				items : [ {
					iconCls : 'icon-view',
					tooltip : '查看',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'View'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.UserManagementWindow({
							hidden : true
						});
						var form = win.down('form').getForm();
						form.loadRecord(gridRecord);
						form.findField('userName').setReadOnly(true);
						form.findField('password').hide();
						form.findField('realName').setReadOnly(true);
						form.findField('tel').setReadOnly(true);
						form.findField('email').setReadOnly(true);
						form.findField('lastLoginTime').show().setReadOnly(true);
						form.findField('roleName').setValue(gridRecord.get('roleName')).setReadOnly(true);
						Ext.getCmp('usermanagementwindow-save').hide();
						Ext.getCmp('usermanagementwindow-cancel').hide();
						Ext.getCmp('usermanagementwindow-accept').show();
						win.show();
					}
				}, {
					iconCls : 'edit',
					tooltip : '修改',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Edit'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.UserManagementWindow({
							hidden : true
						});
						win.setHeight(250);
						var form = win.down('form').getForm();
						form.loadRecord(gridRecord);
						form.findField("cmd").setValue("edit");
						form.findField("userName").setReadOnly(true);
						form.findField('password').hide();
						// form.findField('roleName').setRawValue(gridRecord.get('roleName'));
						win.show();
					}
				}, {
					iconCls : 'icon-delete',
					tooltip : '删除',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Delete'),
					handler : function(grid, rowIndex, colIndex) {
						var entity = grid.getStore().getAt(rowIndex);
						singleId = entity.get('id');
						me.onDeleteClick();
					}
				} ]
			} ];

			Ext.apply(this, {
				id : 'usermanagementgrid',
				store : store,
				columns : columns
			});

			store.loadPage(1);

			this.callParent(arguments);
		},
		onAddClick : function() {
			new App.UserManagementWindow().show();
		},
		onViewClick : function() {
			var grid = Ext.getCmp("usermanagementgrid");
			var id = grid.getSelectionModel().getSelection()[0].get('id');
			var gridRecord = grid.getStore().findRecord('id', id);
			var win = new App.UserManagementWindow({
				hidden : true,
				height : 230
			});
			var form = win.down('form').getForm();
			form.loadRecord(gridRecord);
			form.findField('userName').setReadOnly(true);
			form.findField('password').hide();
			form.findField('realName').setReadOnly(true);
			form.findField('tel').setReadOnly(true);
			form.findField('email').setReadOnly(true);
			form.findField('lastLoginTime').show().setReadOnly(true);
			form.findField('roleName').setValue(grid.getSelectionModel().getSelection()[0].get('roleName')).setReadOnly(true);
			Ext.getCmp('usermanagementwindow-save').hide();
			Ext.getCmp('usermanagementwindow-cancel').hide();
			Ext.getCmp('usermanagementwindow-accept').show();
			win.show();
		}
	});
});