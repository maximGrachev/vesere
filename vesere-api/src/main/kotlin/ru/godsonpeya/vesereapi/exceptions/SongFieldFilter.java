package ru.godsonpeya.vesereapi.exceptions;//package ru.godsonpeya.atmosphere.exceptions;

//public enum SongFieldFilter {
//
//
//    SONG_BOOK_ID(((filter, songSpecification)->{
//        if (filter.getSongbookId()!=null){
//            songSpecification = songSpecification.and(hasSongBook(filter.getSongbookId()));
//        }
//        return songSpecification;
//    }));
//    private final BiFunction<SongFilter, Specification<Song>, Specification<Song>> combine;
//
//    SongFieldFilter(BiFunction<SongFilter, Specification<Song>, Specification<Song>> combine) {
//        this.combine = combine;
//    }
//
//    public Specification<Song> chain(SongFilter filter, Specification<Song> combined){
//        return combine.apply(filter, combined);
//    }
//}
