<template lang="jade">
    .app
        router-view
        tabbar
</template>

<script>
module.exports = {
    components:{
        tabbar:require('./components/Tabbar.vue'),
    }
}
</script>

<style lang="sass">
@import './style.scss';

.app {
    position: relative;
}

input,textarea,button {
	outline:none;
	border:none;
	border-radius: none;
	background: transparent;
}

html,body {
	margin:0;
    background: $COLOR_BG;
    color:#232323;
}

a {
    text-decoration: none;
    color:#232323;
}

html,body,input {
	font-family: ".PingFang-SC-Default", "Helvetica Neue", Helvetica,  "Microsoft Yahei",Arial, "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
}

.hide-scroll::-webkit-scrollbar,
.hide-scroll::-webkit-scrollbar-track,
.hide-scroll::-webkit-scrollbar-thumb,
body::-webkit-scrollbar,
body::-webkit-scrollbar-track,
body::-webkit-scrollbar-thumb {
    width:0;
    border-width: 0;
    height:0;
}

</style>