<script>
export default {
  data() {
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    tryLogin() {
      var pwdObj = this.password;
      var hashObj = new jsSHA("SHA-512", "TEXT", {numRounds: 1});
      hashObj.update(pwdObj);
      var hash = hashObj.getHash("HEX");
      pwdObj = hash;
      alert("username: " + this.username + "\npaswword: " + pwdObj);
    }
  }
}
  </script>

  <template>
    <h1>Login Page</h1>
    <form>
      <label for="username">Username:</label>
      <input v-model="username" type="text" id="username" name="username" required>
      <label for="password">Password:</label>
      <input v-model="password" type="password" id="password" name="password" required>
      <button @click="tryLogin" type="submit" style="height: 20px;width: 60px">Login</button>
    </form>
  </template>

  <style></style>
