<template>
  <div class="d-flex p-2 justify-content-center">
    <form accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Log in</h1>

      <label class="p-2">E-mail</label>
      <input type="text" class="p-2" id="email" v-model="email" />
      <label class="p-2" for="password">Password</label>
      <input
        type="password"
        class="p-2"
        id="password"
        v-model="password"
        placeholder="Password"
      />
      <br />
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "login",
  props: {
    // props
  },
  data: function() {
    return {
      email: undefined,
      password: undefined
    };
  }
};
</script>
