function showShorts() {
    const contentArea = document.getElementById('content-area');
    contentArea.innerHTML = '';
    const heading = document.createElement('h1');
    heading.textContent = 'Shorts';
    contentArea.appendChild(heading);
}
