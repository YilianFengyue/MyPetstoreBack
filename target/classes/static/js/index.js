
new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    // 设置栏数据
    data:{
     //帮助对话框
    helpDialog:false,
      //加载进度条
    loading: false,
    selection: 1,
    username:"admin",
    password:"1"
    },

    methods:{
        async reserve() {
            this.loading = true;
            try {
                const response = await axios.post('/signon/login', {
                    username: this.username,
                    password: this.password
                }, {
                    headers: { 'Content-Type': 'application/json' }
                });

                const result = response.data;
                console.log("登录响应:", result); // 这里打印后端返回的数据

                // **确保匹配正确的字段**
                if (result.status && result.status === 'success') {
                    alert("登录成功！");
                    window.location.href = '/pages/商品管理.html';
                } else {
                    alert(result.message || "登录失败，请检查用户名或密码！");
                    return; // **明确 return，防止跳转**
                }
            } catch (error) {
                console.error("请求失败", error);
                alert("系统错误，请稍后再试！");
            } finally {
                this.loading = false;
            }
        }


    }
  })
