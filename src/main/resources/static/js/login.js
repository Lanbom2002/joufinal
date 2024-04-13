document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止表单默认提交行为

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // 这里可以添加发送请求到服务器的代码，例如使用 fetch 或 XMLHttpRequest
    alert('Logging in with Username: ' + username + ' and Password: ' + password);
});