
    new Vue({
      el: '#app',
      vuetify: new Vuetify(),
      // 设置栏数据
      data:{
        
        cards: ['Today'],
        //设置开关
        settingDialog:false,
        tableDialog: false,
        notifications: false,
        sound: true,
        widgets: false,
      
      //侧边栏数据
      sideDrawer: true,
      sideDrawerlinks: [
        { icon: 'mdi-microsoft', text: '商品管理', url: '商品管理.html' },
        { icon: 'mdi-cart-minus', text: '商品小类管理', url: '商品小类管理.html' },
        { icon: 'mdi-dog', text: '宠物管理', url: '宠物管理.html' },
        { icon: 'mdi-account-cog', text: '用户管理', url: '用户管理.html' },
        { icon: 'mdi-nintendo-switch', text: '订单管理', url: '订单管理.html' }
      ],
       //消息条
        snackbar: false,
        text: `密码已重置`,
      //表格数据
      dialog: false,
      resetDialog: false,
      dialogDelete: false,
      headers: [
        {
          text: 'Username',
          align: 'start',
          sortable: false,
          value: 'userid',
        },
        { text: 'Password', value: 'password' ,sortable: false},
        { text: 'Email', value: 'email' ,sortable: false},
        { text: 'FirstName', value: 'firstname' ,sortable: false},
        { text: 'LastName', value: 'lastname' ,sortable: false},
        { text: 'Status', value: 'status' ,sortable: false},
        { text: 'Actions', value: 'actions', sortable: false },

      ],
      Accounts: [],
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
      }},
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
          axios.get("/account").then((res)=>{
            this.Accounts = res.data.data;

          });
        },
        //编辑用户信息

        editItem (item) {
          this.editedIndex = this.Accounts.indexOf(item)
          this.editedItem = Object.assign({}, item)
          this.dialog = true
        },


        deleteItem (item) {
          this.editedIndex = this.Accounts.indexOf(item)
          this.editedItem = Object.assign({}, item)
          this.dialogDelete = true
        },

        deleteItemConfirm () {
          // this.Accounts.splice(this.editedIndex, 1)
          // this.closeDelete()
          const userId = this.editedItem.userid; // 获取要删除的用户 ID
          console.log(userId)
          axios.delete(`/account/${userId}`)
              .then((res) => {
                console.log("删除成功:", res.data);
                alert("用户删除成功！");

                // 在前端同步删除
                this.Accounts.splice(this.editedIndex, 1);
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
            // Object.assign(this.Accounts[this.editedIndex], this.editedItem)
            //发送ajax请求
            console.log(this.editedItem)
            axios.put("/account",this.editedItem).then((res)=>{

            }).finally(()=>{
              this.getAll();
            });
          } else {
            //2.新增操作
            this.Accounts.push(this.editedItem)
          }
          this.close()
        },

        //页面跳转
        goToPage(pageName) {
          switch (pageName) {
            case '商品管理':
              this.$router.push('/products');
              break;
            case '用户管理':
              this.$router.push('/users');
              break;
            case '订单管理':
              this.$router.push('/orders');
              break;
            default:
              break;
          }
        }
      }
    })
