package zio

package object redis
    extends api.Connection
    with api.Hashes
    with api.HyperLogLog
    with api.Keys
    with api.Lists
    with api.Sets
    with api.Strings
    with api.SortedSets
