package pt.penguin.data.datasource

import pt.penguin.data.cache.ValueMemoryDataSource
import pt.penguin.data.model.home.ArtObjectDataModel
import pt.penguin.domain.model.home.MuseumCollection
import javax.inject.Inject

class MuseumMemoryDataSource @Inject constructor(): ValueMemoryDataSource<List<ArtObjectDataModel>>()