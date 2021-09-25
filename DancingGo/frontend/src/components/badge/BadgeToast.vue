<template>
  <div>
    <b-button @click="showToast">Show Toast with custom content</b-button>
    <b-button @click="$bvToast.show('my-toast')">ssss</b-button>

    <b-toast id="my-toast" variant="info" solid toaster='b-toaster-top-center'>
      <template #toast-title>
        <div class="d-flex flex-grow-1 align-items-baseline">
          <b-img blank blank-color="#ff5555" class="mr-2" width="12" height="12"></b-img>
          <strong class="mr-auto">Notice!</strong>
          <small class="text-muted mr-2">42 seconds ago</small>
        </div>
      </template>
      <div class="d-flex badge_content">
        <img src="images/badgeImg/1001.png" width="100vw">
        <h3>시작이 반이다!</h3>
      </div>
    </b-toast>

    <!-- <img src="images/badgeImg/1001.png"> -->
  </div>
</template>

<script>
  export default {
    data() {
      return {
        count: 0
      }
    },
    methods: {
      showToast() {
        // Use a shorter name for this.$createElement
        const h = this.$createElement
        // var im = this.$$createElement("img")
        // im.src="images/badgeImg/1001.png"
        // Increment the toast count
        this.count++
        // Create the message
        const vNodesMsg = h('img',{src : ["images/badgeImg/1001.png"]})
        // const vNodesMsg = h(
        //   // 'p',
        //   // { class: ['text-center', 'mb-0'] },
        //   // [
        //   //   h('b-spinner', { props: { type: 'grow', small: true } }),
        //   //   ' Flashy ',
        //   //   h('strong', 'toast'),
        //   //   ` message #${this.count} `,
        //   //   h('b-spinner', { props: { type: 'grow', small: true } }),
        //   // ],
        //   'img',
        //   { src: ["images/badgeImg/1001.png"] },
        //   [

        //   ]
        // )
        // Create the title
        const vNodesTitle = h(
          'div',
          { class: ['d-flex', 'flex-grow-1', 'align-items-baseline', 'mr-2'] },
          [
            h('strong', { class: 'mr-2' }, 'The Title'),
            h('small', { class: 'ml-auto text-italics' }, '5 minutes ago')
          ]
        )
        // Pass the VNodes as an array for message and title
        this.$bvToast.toast([vNodesMsg], {
          title: [vNodesTitle],
          solid: true,
          variant: 'info',
          toaster : 'b-toaster-top-center'
        })
      }
    }
 }
</script>

<style>
.badge_content{
  /* display: inline-block; */
  width: 100%;
}



</style>