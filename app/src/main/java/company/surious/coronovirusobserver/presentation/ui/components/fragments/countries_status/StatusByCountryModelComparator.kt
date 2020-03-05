package company.surious.coronovirusobserver.presentation.ui.components.fragments.countries_status

object StatusByCountryModelComparator :
    Comparator<StatusByCountryModel> {
    override fun compare(o1: StatusByCountryModel, o2: StatusByCountryModel): Int =
        when {
            o1.count > o2.count -> -1
            o1.count == o2.count -> o1.countryName.compareTo(o2.countryName)
            else -> 1
        }
}