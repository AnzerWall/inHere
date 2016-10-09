/**
 * Created by anzer on 2016/10/8.
 */
var win=window;
var doc = win.document;
var docEl = doc.documentElement;
var tid;

function refreshRem() {
  var width = docEl.getBoundingClientRect().width;
  if (width > 720) { // 最大宽度
    width = 720;
  }
  var rem = width / 375*16; // 将屏幕宽度分成10份， 1份为1rem
  console.log('rem');
  docEl.style.fontSize = rem + 'px';
}
function init(){

  win.addEventListener('resize', function() {
    clearTimeout(tid);
    tid = setTimeout(refreshRem, 300);
  }, false);
  win.addEventListener('pageshow', function(e) {
    if (e.persisted) {
      clearTimeout(tid);
      tid = setTimeout(refreshRem, 300);
    }
  }, false);
  refreshRem();
}


export default{
  init,
  refreshRem
};


