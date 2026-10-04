import type { EntityDefinitions, TabId } from './types'

const COLORS = ['', 'GREEN', 'BLACK', 'YELLOW', 'ORANGE'] as const
const COUNTRIES = ['', 'FRANCE', 'INDIA', 'VATICAN', 'SOUTH_KOREA', 'NORTH_KOREA'] as const
const RATINGS = ['', 'PG_13', 'R', 'NC_17'] as const
const GENRES = ['', 'WESTERN', 'TRAGEDY', 'THRILLER', 'HORROR', 'FANTASY'] as const

export const ENTITY_TABS: ReadonlyArray<{ id: TabId; label: string }> = [
  { id: 'movies', label: 'Movies' },
  { id: 'persons', label: 'Persons' },
  { id: 'locations', label: 'Locations' },
  { id: 'coordinates', label: 'Coordinates' },
  { id: 'operations', label: 'Special ops' },
]

export const ENTITY_DEFINITIONS: EntityDefinitions = {
  movies: {
    title: 'MOVIE DATABASE',
    typeName: 'MOVIE',
    statusNoun: 'records',
    searchableKeys: ['name'],
    columns: [
      { key: 'id', label: 'ID', readonly: true },
      { key: 'name', label: 'NAME' },
      { key: 'coordinatesId', label: 'COORDINATES ID', relation: 'coordinates' },
      { key: 'creationDate', label: 'CREATION DATE', readonly: true },
      { key: 'oscarsCount', label: 'OSCARS' },
      { key: 'budget', label: 'BUDGET' },
      { key: 'totalBoxOffice', label: 'TOTAL BOX OFFICE' },
      { key: 'mpaaRating', label: 'MPAA RATING', options: RATINGS },
      { key: 'directorId', label: 'DIRECTOR ID', relation: 'persons' },
      { key: 'screenwriterId', label: 'SCREENWRITER ID', relation: 'persons' },
      { key: 'operatorId', label: 'OPERATOR ID', relation: 'persons' },
      { key: 'length', label: 'LENGTH' },
      { key: 'goldenPalmCount', label: 'GOLDEN PALM COUNT' },
      { key: 'genre', label: 'GENRE', options: GENRES },
    ],
  },
  persons: {
    title: 'PERSON DIRECTORY',
    typeName: 'PERSON',
    statusNoun: 'people',
    searchableKeys: ['name'],
    columns: [
      { key: 'id', label: 'ID', readonly: true },
      { key: 'name', label: 'NAME' },
      { key: 'eyeColor', label: 'EYE COLOR', options: COLORS },
      { key: 'hairColor', label: 'HAIR COLOR', options: COLORS.slice(1) },
      { key: 'locationId', label: 'LOCATION ID', relation: 'locations' },
      { key: 'height', label: 'HEIGHT' },
      { key: 'nationality', label: 'NATIONALITY', options: COUNTRIES },
    ],
  },
  locations: {
    title: 'LOCATION INDEX',
    typeName: 'LOCATION',
    statusNoun: 'locations',
    searchableKeys: ['name'],
    columns: [
      { key: 'id', label: 'ID', readonly: true },
      { key: 'x', label: 'X' },
      { key: 'y', label: 'Y' },
      { key: 'z', label: 'Z' },
      { key: 'name', label: 'NAME' },
    ],
  },
  coordinates: {
    title: 'COORDINATE REGISTER',
    typeName: 'COORDINATES',
    statusNoun: 'points',
    searchableKeys: [],
    columns: [
      { key: 'id', label: 'ID', readonly: true },
      { key: 'x', label: 'X' },
      { key: 'y', label: 'Y' },
    ],
  },
}
