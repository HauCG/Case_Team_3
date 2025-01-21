function showSubscriptions() {
    const contentArea = document.getElementById('content-area');
    contentArea.innerHTML = '';
    const heading = document.createElement('h1');
    heading.textContent = 'Kênh đăng ký';
    contentArea.appendChild(heading);
}
