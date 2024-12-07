let container = document.getElementById('container')

toggle = () => {
	container.classList.toggle('sign-in')
	container.classList.toggle('sign-up')
}
function toggleMenu() {
	const navLinks = document.querySelector('.nav-links');
	navLinks.classList.toggle('active');
  }
  

setTimeout(() => {
	container.classList.add('sign-in')
}, 200)