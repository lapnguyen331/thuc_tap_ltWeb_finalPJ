const icon = document.querySelectorAll('.service i');

icon.forEach((el) => {
  el.addEventListener('onHover.mdb.rating', () => {
    console.log('onHover');
  });
  el.addEventListener('onSelect.mdb.rating', () => {
    console.log('onSelect');
  });
})