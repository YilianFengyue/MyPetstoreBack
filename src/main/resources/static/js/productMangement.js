
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
          text: 'Product',
          align: 'start',
          sortable: false,
          value: 'productid',
        },
        { text: 'Name', value: 'name' ,sortable: false},
        { text: 'Category', value: 'category' ,sortable: false},
        { text: '图片', value: 'descn' ,sortable: false},
        { text: 'Actions', value: 'actions', sortable: false },

      ],
      switch1: true,
      //搜索
      search: '',
      Products: [
        {
            "productid": "1919810",
            "category": "114514",
            "name": "Updated Product A",
            "descn": null
        },
        {
            "productid": "AV-CB-01",
            "category": "BIRDS",
            "name": "Amazon Parrot",
            "descn": "<image src=\"images/bird2.gif\">Great companion for up to 75 years"
        },
      ],
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
      },
      selectedFile: null, // 存储选中的文件
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
      axios.get("/product").then((res)=>{
        this.Products = res.data.data;
      });
    },
    //编辑用户信息

    editItem (item) {
      this.editedIndex = this.Products.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },


    deleteItem (item) {
      this.editedIndex = this.Products.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm () {
      // this.Products.splice(this.editedIndex, 1)
      // this.closeDelete()
      const productid = this.editedItem.productid; // 获取要删除的用户 ID
      console.log(productid)
      axios.delete(`/product/${productid}`)
          .then((res) => {
            console.log("删除成功:", res.data);
            alert("类型删除成功！");

            // 在前端同步删除
            this.Products.splice(this.editedIndex, 1);
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
        // Object.assign(this.Products[this.editedIndex], this.editedItem)
        //发送ajax请求
        console.log(this.editedItem)

        axios.put("/product",this.editedItem).then((res)=>{

        }).finally(()=>{
          this.getAll();
        });
      } else {
        //2.新增操作
        axios.post("/product",this.editedItem).then((res)=>{

        }).finally(()=>{
          this.getAll();
        })
        this.Products.push(this.editedItem)
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
    //商品上传
    //upload
    async uploadFile() {
      if (!this.selectedFile) return;

      const formData = new FormData();
      formData.append("file", this.selectedFile);
      const fileName = this.selectedFile.name;  // 获取文件名
      console.log(fileName)
      this.editedItem.descn=`<image src="../images/${fileName}">`;
      console.log(this.editedItem.descn );
      try {
        const response = await axios.post("/product/upload", formData, {
          headers: {"Content-Type": "multipart/form-data"},
        });
        console.log("上传成功:", response.data);
      } catch (error) {
        console.error("上传失败:", error);
      }

    },
    //图片路径修改
    updateImagePaths(descn) {
      if (!descn) return ""; // 防止为空值报错

      const updatedDesc = descn.replace(/<image src="images\//g, '<img src="../images/');

      return updatedDesc;
    },
  }
  })
