
new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    // 设置栏数据
    data:{
      sideDrawer: true,
      cards: ['Today'],
      //设置开关
      settingDialog:false,
      tableDialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      //侧边栏数据
      sideDrawerlinks: [
        { icon: 'mdi-microsoft', text: '商品管理', url: '商品管理.html' },
        { icon: 'mdi-cart-minus', text: '商品小类管理', url: '商品小类管理.html' },
        { icon: 'mdi-dog', text: '宠物管理', url: '宠物管理.html' },
        { icon: 'mdi-account-cog', text: '用户管理', url: '用户管理.html' },
        { icon: 'mdi-nintendo-switch', text: '订单管理', url: '订单管理.html' }
      ],
      snackbar: false,
      text: `密码已重置`,
      //表格数据
      dialog: false,
      resetDialog: false,
      dialogDelete: false,
      headers: [
        {
          text: 'itemid',
          align: 'start',
          sortable: false,
          value: 'itemid',
        },
        { text: 'productid', value: 'productid' ,sortable: false},
        { text: 'listprice', value: 'listprice' ,sortable: false},
        { text: 'unitcost', value: 'unitcost' ,sortable: false},
        { text: 'attr1', value: 'lastname' ,sortable: false},
        { text: '上架', value: 'uploaded' ,sortable: false},
        { text: 'Actions', value: 'actions', sortable: false },

      ],
      switch1: true,
      //搜索
      search: '',
      Items: [],
      editedIndex: -1,
      editedItem: {
        userid: '',
        password: 0,
        email: 0,
        lastname: 0,
        firstname: 0,
      },
      defaultItem: {
        userid: '',
        password: 0,
        email: 0,
        lastname: 0,
        firstname: 0,


      }
    },
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
  },

  watch: {
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
  },

  created () {
    this.initialize()
  },
  methods:{
    //设置开关
    toggleDrawer() {
      this.sideDrawer = !this.sideDrawer;
    },
    //表格管理
    //初始化，调用getall()
    initialize () {
      this.getAll();

    },
    getAll(){
      //发送ajax请求
      axios.get("/item").then((res)=>{
        this.Items = res.data.data;
      });
    },
    //编辑用户信息

    editItem (item) {
      this.editedIndex = this.Items.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },


    deleteItem (item) {
      this.editedIndex = this.Items.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm () {
      // this.Items.splice(this.editedIndex, 1)
      // this.closeDelete()
      const itemid = this.editedItem.itemid; // 获取要删除的用户 ID
      console.log(itemid)
      axios.delete(`/item/${itemid}`)
          .then((res) => {
            console.log("删除成功:", res.data);
            alert("用户删除成功！");

            // 在前端同步删除
            this.Items.splice(this.editedIndex, 1);
          })
          .catch((err) => {
            console.error("删除失败:", err);
            alert("删除失败，请稍后再试！");
          })
          .finally(() => {
            this.closeDelete();
          });
    },

    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    //重置密码
    openResetDialog(item) {
      this.editedItem = Object.assign({}, item);
      this.resetDialog = true;
    },
    resetPassword () {
      console.log(this.editedItem)
      this.resetDialog = false;
      axios.put("/account/reset-password", {
        username: this.editedItem.userid,
        newPassword: "1234"
      }).then((res) => {
        console.log("密码重置成功:", res.data);
        this.snackbar = true;
      }).catch((err) => {
        console.error("重置密码失败:", err);
      }).finally(() => {
        this.resetDialog = false;

      });
    },
    closeDelete () {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    save () {
      if (this.editedIndex > -1) {
        //1.编辑操作
        // Object.assign(this.Items[this.editedIndex], this.editedItem)
        //发送ajax请求
        console.log(this.editedItem)

        axios.put("/item",this.editedItem).then((res)=>{

        }).finally(()=>{
          this.getAll();
        });
      } else {
        //2.新增操作
        axios.post("/item",this.editedItem).then((res)=>{

        }).finally(()=>{
          this.getAll();
        })
        this.Items.push(this.editedItem)
      }
      this.close()
    },
    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    //上架商品
    updateUploadStatus() {
      // 将状态从布尔值（true/false）转换为 1 或 0

      console.log("更新后的状态:", this.Item.uploaded);

    },

  }
  })
