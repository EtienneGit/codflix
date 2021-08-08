$(document).ready(function() {

  $( '#sidebarCollapse' ).on( 'click', function() {

    $( '#sidebar' ).toggleClass('open');

  });

  let tag = document.createElement('script');
  tag.src = "https://www.youtube.com/iframe_api";
  let firstScriptTag = document.querySelectorAll('script')[0];
  console.log(firstScriptTag);
  firstScriptTag.insertBefore(tag, null);

});
