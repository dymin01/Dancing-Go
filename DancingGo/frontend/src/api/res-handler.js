export default {
  handle (success, fail = (message) => { console.log(message) }) {
    return (res) => {
      switch (res.data.header.code) {
        case 200: {
          console.log(res.data.body)
          success(res.data.body)
          break
        }
        default: fail(res.data.header.message)
      }
    }
  }
}
