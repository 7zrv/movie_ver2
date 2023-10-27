function setPageNation(totalPage, currentPage, groupSize){
    let pageHtml = ``;
    const pageGroup = Math.ceil(currentPage / groupSize);
    let groupLast = pageGroup * groupSize;
    let groupFirst = groupLast - (groupSize - 1);

    if (groupLast > totalPage) groupLast = totalPage;

    if(groupFirst !== 1) pageHtml += `<a class="arrow pprev" href="#1"></a><a class="arrow prev" href="#${groupFirst-groupSize}"></a>`;
    for(let i=groupFirst; i<=groupLast; i++) {
        if(i == currentPage){
            pageHtml += `<a href="#${i}" class="active">${i}</a>`
        }
        else {
            pageHtml += `<a href="#${i}">${i}</a>`;
        }
    }
    if(groupLast !== totalPage) pageHtml += `<a class="arrow next" href="#${groupFirst+groupSize}"></a><a class="arrow nnext" href="#${totalPage}"></a>`;

    $('.page_nation').html($(pageHtml));
}