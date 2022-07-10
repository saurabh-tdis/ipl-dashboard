export class Pageable{
    page: number;
    size: number;
    sort: string[]

    constructor(page,size,sort) {
        this.page=page;
        this.size=size;
        this.sort=[sort]
    }
}