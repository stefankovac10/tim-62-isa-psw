<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
    <div v-if="mode == 'VIEW'">
      <div
        class="card border-primary mb-3"
        style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
      >
        <div class="card-header">Address</div>
        <div class="card-body">
          <h4 class="card-title">Name</h4>
          <p class="card-text">
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </p>
          <button type="button" class="btn btn-primary" v-on:click="edit">
            Edit
          </button>
          <button type="button" class="btn btn-danger" v-on:click="remove">
            Delete
          </button>
        </div>
      </div>
    </div>
    <div v-else>
      <form>
        <fieldset>
          <legend>Add clinic</legend>
          <div class="form-group">
            <label for="exampleInputEmail1">Name</label>
            <input
              type="text"
              class="form-control"
              id="nameClinic"
              v-model="name"
              placeholder="Enter name"
            />
          </div>
          <div class="form-group">
            <label for="exampleInputEmail1">Address</label>
            <input
              type="text"
              class="form-control"
              id="addressClinic"
              v-model="address"
              placeholder="Enter address"
            />
          </div>
          <div class="form-group">
            <label for="exampleTextarea">Description</label>
            <textarea
              class="form-control"
              id="descriptionClinic"
              v-model="description"
              rows="3"
            ></textarea>
          </div>
          <button type="submit" class="btn btn-primary" v-on:click="save">
            Save
          </button>
        </fieldset>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "clinics",
  data: function() {
    return {
      name: undefined,
      address: undefined,
      description: undefined,
      mode: "VIEW"
    };
  },
  methods: {
    edit: function() {
      this.mode = "EDIT";
    },
    save: function() {
      this.mode = "VIEW";
    },
    remove: function() {}
  }
};
</script>
