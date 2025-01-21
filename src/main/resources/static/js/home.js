function showHome() {
    const contentArea = document.getElementById('content-area');
    contentArea.innerHTML = '';
    const heading = document.createElement('h1');
    heading.textContent = 'Trang chủ';
    contentArea.appendChild(heading);
}
