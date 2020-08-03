// https://ru.wikibooks.org/wiki/Реализации_алгоритмов/Сортировка/Быстрая

private int partition(Object[] array, int begin, int end, Comparator cmp) {
    int index = begin + RND.nextInt(end - begin + 1);
    Object pivot = array[index];
    swap(array, index, end);
    for (int i = index = begin; i < end; ++ i) {
        if (cmp.compare(array[i], pivot) <= 0) {
            swap(array, index++, i);
        }
    }
    swap(array, index, end);
    return (index);
}

private int partition (Object[] array, int begin, int end, Comparator cmp)
{
    int    index = begin + RND.nextInt(end - begin + 1);
    Object pivot = array[index];

    swap(array, index, end);

    for (int i = index = begin; i < end; ++ i)
    {
        if (cmp.compare(array[i], pivot) <= 0) {
            swap(array, index++, i);
        }
    }
    
    swap(array, index, end);

    return (index);
}